package com.space.quiz.presentation.ui_questions.ui

import androidx.navigation.fragment.findNavController
import com.space.quiz.R
import com.space.quiz.databinding.FragmentTestsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_questions.vm.QuestionsViewModel
import com.space.quiz.presentation.views.HeaderView
import kotlin.reflect.KClass

class QuestionsFragment : BaseFragment<FragmentTestsBinding, QuestionsViewModel>() {

    override val viewModelClass: KClass<QuestionsViewModel>
        get() = QuestionsViewModel::class

    override fun inflate(): Inflater<FragmentTestsBinding> {
        return FragmentTestsBinding::inflate
    }

    override fun onBind(viewModel: QuestionsViewModel) {
        showTitleVIew()
    }

    private fun showTitleVIew() {
        val titleView = HeaderView(requireContext())
        binding.root.addView(titleView)
        titleView.subjectTitleToolBar()
        titleView.setCancelButton {
            findNavController().navigate(R.id.action_testFragment_to_homeFragment)
            //navigateTo(R.id.action_testFragment_to_homeFragment)
        }
    }
}