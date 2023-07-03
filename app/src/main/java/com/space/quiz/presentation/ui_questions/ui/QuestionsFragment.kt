package com.space.quiz.presentation.ui_questions.ui

import android.annotation.SuppressLint
import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import com.space.quiz.R
import com.space.quiz.databinding.FragmentQuestionsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_questions.adapter.AnswersAdapter
import com.space.quiz.presentation.ui_questions.vm.QuestionsViewModel
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.utils.lifecycleScope
import kotlin.reflect.KClass

class QuestionsFragment : BaseFragment<FragmentQuestionsBinding, QuestionsViewModel>() {

    override val viewModelClass: KClass<QuestionsViewModel>
        get() = QuestionsViewModel::class

    override fun inflate(): Inflater<FragmentQuestionsBinding> {
        return FragmentQuestionsBinding::inflate
    }

    private val answersAdapter by lazy {
        AnswersAdapter()
    }
    private val args: QuestionsFragmentArgs by navArgs()

    override fun onBind() {
        val subject = args.subject
        val item = args.item
        viewModel.quizSubjects = item
        binding.customTitleView.customText = subject
        setQuestions()
        setCancelTest()
        fetchQuestion(subject)
        viewModel.getNextQuestion(subject)
        setNextQuestion(subject)
        initRecycler()
        backButton()
    }

    private fun initRecycler() {
        binding.answersRecyclerView.apply {
            adapter = answersAdapter
        }
    }


    private fun fetchQuestion(subject: String) {
        lifecycleScope {
            viewModel.fetchQuestions(subject)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setQuestions() {
        lifecycleScope {
            viewModel.questions.collect {
                it?.let {
                    binding.testsQuestionTextView.text = it.questionTitle
                    answersAdapter.submitList(it.answers)
                    answersAdapter.submitCorrectAnswer(it.correctAnswer)
                    binding.progressBarView.setMaxSize(viewModel.getQuestionSize())
                    if (viewModel.isLastQuestion()) {
                        binding.nextQuestionButton.text = getString(R.string.finish_text)
                    } else binding.nextQuestionButton.text = getString(R.string.next_button_text)
                    answersAdapter.notifyDataSetChanged()
                    //answersAdapter.notifyItemRangeChanged(0, it.answers.size)
                }
            }
        }
        lifecycleScope {
            viewModel.finalScore.collect { point ->
                point?.let {
                    if (point != 0){
                        showCongratsDialog(point.toString())
                    }else{
                        showFailedDialog(point.toString())

                    }
                }
            }
        }
    }

    private fun setNextQuestion(subject: String) {
        binding.progressBarView.setProgress(viewModel.getQuestionIndex()+1)
        binding.nextQuestionButton.setOnClickListener {
            if (answersAdapter.isAnswered()){
                val selectedAnswer = answersAdapter.getSelectedAnswer()
                if (selectedAnswer != null) {
                    val isAnswerCorrect = selectedAnswer == answersAdapter.getCorrectAnswer()
                    viewModel.updatePoints(isAnswerCorrect)
                }
                viewModel.getNextQuestion(subject)
                binding.progressBarView.setProgress(viewModel.getQuestionIndex() + 1)
                binding.progressBarView.setPoints(viewModel.getCurrentScore())
            }
        }
    }

    private fun setCancelTest() {
        binding.customTitleView.setCancelButton {
            showCancelDialog()
        }
    }

    private fun showCancelDialog() {
        CustomDialogView(requireContext()).apply {
            showCloseState()
            setNeutralButtonClickListener {}
            setNegativeButtonClickListener {}
            setPositiveButtonClickListener {
                viewModel.navigateToHome()
            }
            showDialog()
        }
    }

    private fun showCongratsDialog(point: String) {
        CustomDialogView(requireContext()).apply {
            showCongratsState()
            setText(requireContext().getString(R.string.points_text, point.toInt()))
            setNeutralButtonClickListener {
                viewModel.navigateToHome()
            }
            showDialog()
        }
    }

    private fun showFailedDialog(point: String){
        CustomDialogView(requireContext()).apply {
            showFailedState()
            setText(requireContext().getString(R.string.points_text, point.toInt()))
            setNeutralButtonClickListener {
                viewModel.navigateToHome()
            }
            showDialog()
        }
    }

    private fun backButton(){
        requireActivity().onBackPressedDispatcher.addCallback {
            showCancelDialog()
        }
    }
}