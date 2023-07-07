package com.space.quiz.presentation.ui_intro.ui

import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.space.quiz.R
import com.space.quiz.databinding.FragmentIntroBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_intro.vm.IntroViewModel
import com.space.quiz.presentation.model.UserUIModel
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.utils.collectInLifecycleScope
import com.space.quiz.utils.showToast
import kotlin.reflect.KClass

class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>() {

    override val viewModelClass: KClass<IntroViewModel>
        get() = IntroViewModel::class

    override fun inflate(): Inflater<FragmentIntroBinding> {
        return FragmentIntroBinding::inflate
    }

    override fun onBind() {
        authenticateUser()
        observeSessionAndNavigate()
        backButton()
    }

    private fun observeSessionAndNavigate() {
        viewModel.getCurrentUserSession()
    }

    private fun authenticateUser() {
        collectStatus()
        with(binding) {
            introStartQuizButton.setOnClickListener {
                val currentUser = inputNameEditText.text.toString()
                if (inputNameEditText.text.toString().isNotBlank()) {
                    viewModel.authenticateUser(UserUIModel(userName = currentUser, gpa = 0.0f))
                } else {
                    requireContext().showToast(getString(R.string.insert_username_text))
                }
            }
        }
    }

    private fun collectStatus() {
        collectInLifecycleScope(viewModel.errorFlow) {
            it?.let {
                binding.inputNameEditText.error = getString(R.string.invalid_username_text)
            }
        }
    }
    private fun backButton() {
        requireActivity().onBackPressedDispatcher.addCallback {
            showDialog()
        }
    }
    private fun showDialog() {
        CustomDialogView(requireContext()).apply {
            showExitState()
            setPositiveButtonClickListener {
                requireActivity().finish()
            }
            setNegativeButtonClickListener {}
            showDialog()
        }
    }
}