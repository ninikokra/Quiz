package com.space.quiz.presentation.intro

import com.space.quiz.R
import com.space.quiz.databinding.FragmentIntroBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.dialog.CustomDialogView

class IntroFragment : BaseFragment<FragmentIntroBinding>() {

    override fun inflate(): Inflater<FragmentIntroBinding> {
        return FragmentIntroBinding::inflate
    }

    override fun onBind() {
        binding.introStartQuizButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val customDialogView = CustomDialogView(requireContext())
        customDialogView.showCongratsState()

        customDialogView.setNeutralButtonClickListener {
            navigateTo(R.id.action_introFragment_to_homeFragment)
        }
        customDialogView.showDialog()
    }
}