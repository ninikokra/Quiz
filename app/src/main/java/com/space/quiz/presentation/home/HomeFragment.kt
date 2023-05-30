package com.space.quiz.presentation.home

import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.dialog.CustomDialogView

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun inflate(): Inflater<FragmentHomeBinding> {
        return FragmentHomeBinding::inflate
    }

    override fun onBind() {
        binding.logOutButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val customDialogView = CustomDialogView(requireContext())
        customDialogView.showExitState()

        customDialogView.setPositiveButtonClickListener {
            navigateTo(R.id.action_homeFragment_to_introFragment)
        }
        customDialogView.setNegativeButtonClickListener {}
        customDialogView.showDialog()
    }
}