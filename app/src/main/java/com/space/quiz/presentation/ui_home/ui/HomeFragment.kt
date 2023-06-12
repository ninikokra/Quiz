package com.space.quiz.presentation.ui_home.ui

import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.presentation.ui_home.vm.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun inflate(): Inflater<FragmentHomeBinding> {
        return FragmentHomeBinding::inflate
    }

    override fun onBind(viewModel: HomeViewModel) {
        binding.logOutButton.setOnClickListener {
            showDialog()
        }
        toDetailsFragment()
        toQuestionsFragment()

    }

    private fun toDetailsFragment() {
        binding.detailsTextViewButton.setOnClickListener {
            //navigateTo(R.id.action_homeFragment_to_detailsFragment)
        }
    }

    private fun toQuestionsFragment() {
        binding.chooseSubjectTextView.setOnClickListener {
            //navigateTo(R.id.action_homeFragment_to_testFragment)
        }
    }

    private fun showDialog() {
        val customDialogView = CustomDialogView(requireContext())
        customDialogView.showExitState()

        customDialogView.setPositiveButtonClickListener {
            //navigateTo(R.id.action_homeFragment_to_introFragment)
        }
        customDialogView.setNegativeButtonClickListener {}
        customDialogView.showDialog()
    }
}