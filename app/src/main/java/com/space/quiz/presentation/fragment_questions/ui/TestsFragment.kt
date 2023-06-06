package com.space.quiz.presentation.fragment_questions.ui

import com.space.quiz.R
import com.space.quiz.databinding.FragmentTestsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.fragment_questions.vm.QuestionsViewModel
import com.space.quiz.presentation.views.CustomTitleView
import kotlin.reflect.KClass

class TestsFragment : BaseFragment<FragmentTestsBinding, QuestionsViewModel>() {

    override val viewModelClass: KClass<QuestionsViewModel>
        get() = QuestionsViewModel::class

    override fun inflate(): Inflater<FragmentTestsBinding> {
        return FragmentTestsBinding::inflate
    }

    override fun onBind(viewModel: QuestionsViewModel) {
        showTitleVIew()
    }

    private fun showTitleVIew() {
        val titleView = CustomTitleView(requireContext())
        binding.root.addView(titleView)
        titleView.subjectTitleToolBar()
        titleView.setCancelButton {
            navigateTo(R.id.action_testFragment_to_homeFragment)
        }
    }
}