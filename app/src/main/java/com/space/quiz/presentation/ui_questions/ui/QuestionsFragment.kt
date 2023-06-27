package com.space.quiz.presentation.ui_questions.ui

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.quiz.R
import com.space.quiz.databinding.FragmentQuestionsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_questions.adapter.AnswersAdapter
import com.space.quiz.presentation.ui_questions.vm.QuestionsViewModel
import com.space.quiz.presentation.views.HeaderView
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
        showTitleVIew()
        setQuestions()
        fetchQuestion(subject)
        viewModel.getNextQuestion(subject)
        setNextQuestion(subject)
        initRecycler()
    }

    private fun fetchQuestion(subject: String) {
        lifecycleScope {
            viewModel.fetchQuestions(subject)
        }
    }

    private fun setQuestions() {
        lifecycleScope {
            viewModel.questions.collect {
                it?.let {
                    binding.testsQuestionTextView.text = it.questionTitle
                    answersAdapter.submitList(it.answers)
                    answersAdapter.submitCorrectAnswer(it.correctAnswer)
                    answersAdapter.notifyItemRangeChanged(0, it.answers.size)
                }
            }
        }
    }

    private fun initRecycler() {
        binding.answersRecyclerView.apply {
            adapter = answersAdapter
        }
    }

    private fun setNextQuestion(subject: String) {
        binding.nextQuestionButton.setOnClickListener {
            viewModel.getNextQuestion(subject)
        }
    }

    private fun showTitleVIew() {
        HeaderView(requireContext()).apply {
            binding.root.addView(this)
            subjectTitleToolBar()
            setCancelButton {
                viewModel.navigateToHome()
            }
        }
    }
}