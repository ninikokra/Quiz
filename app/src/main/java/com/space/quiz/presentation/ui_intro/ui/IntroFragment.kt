package com.space.quiz.presentation.ui_intro.ui

import com.space.quiz.R
import com.space.quiz.databinding.FragmentIntroBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_intro.vm.IntroViewModel
import com.space.quiz.presentation.model.UserUIModel
import com.space.quiz.utils.collectInLifecycleScope
import com.space.quiz.utils.showToast
import kotlin.reflect.KClass

class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>() {

    override val viewModelClass: KClass<IntroViewModel>
        get() = IntroViewModel::class

    override fun inflate(): Inflater<FragmentIntroBinding> {
        return FragmentIntroBinding::inflate
    }

    override fun onBind(viewModel: IntroViewModel) {
        authenticateUser(viewModel)
        observeSessionAndNavigate(viewModel)
    }

    private fun observeSessionAndNavigate(viewModel: IntroViewModel) {
        viewModel.getCurrentUserSession()
    }

    private fun authenticateUser(viewModel: IntroViewModel) {
        with(binding) {
            introStartQuizButton.setOnClickListener {
                val currentUser = inputNameEditText.text.toString()
                if (inputNameEditText.text.toString().isNotBlank()) {
                    viewModel.authenticateUser(UserUIModel(userName = currentUser))
                } else {
                    requireContext().showToast(getString(R.string.insert_username_text))
                }
                collectStatus(viewModel)
            }
        }
    }

    private fun collectStatus(viewModel: IntroViewModel) {
        collectInLifecycleScope(viewModel.errorFlow) {
            binding.inputNameEditText.error = getString(R.string.invalid_username_text)
        }
    }
}