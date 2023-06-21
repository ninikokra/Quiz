package com.space.quiz.presentation.ui_home.ui

import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.presentation.ui_home.vm.HomeViewModel
import com.space.quiz.utils.observe
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun inflate(): Inflater<FragmentHomeBinding> {
        return FragmentHomeBinding::inflate
    }


    override fun onBind(viewModel: HomeViewModel) {
        binding.logOutButton.setOnClickListener {
            showDialog(viewModel)
        }
        observeUsername(viewModel)
    }


    private fun observeUsername(viewModel: HomeViewModel) {
        viewModel.username.observe(viewLifecycleOwner) { username ->
            binding.helloUserTextView.text = getString(R.string.hello_user_text, username)
        }
        viewModel.fetchUsername()
    }

    private fun showDialog(viewModel: HomeViewModel) {
        CustomDialogView(requireContext()).apply {
            showExitState()
            setPositiveButtonClickListener {
                viewModel.logout()
            }
            showDialog()
        }
    }
}