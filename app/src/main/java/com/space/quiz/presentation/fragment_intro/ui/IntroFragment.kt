package com.space.quiz.presentation.fragment_intro.ui

import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.space.quiz.R
import com.space.quiz.databinding.FragmentIntroBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.dialog.CustomDialogView
import com.space.quiz.presentation.fragment_intro.vm.IntroViewModel
import com.space.quiz.presentation.model.PresentationUserModel
import com.space.quiz.utils.Resource
import com.space.quiz.utils.collectInLifecycleScope
import kotlin.reflect.KClass

class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>() {

    override val viewModelClass: KClass<IntroViewModel>
        get() = IntroViewModel::class

    override fun inflate(): Inflater<FragmentIntroBinding> {
        return FragmentIntroBinding::inflate
    }

    override fun onBind(viewModel: IntroViewModel) {
        /* binding.introStartQuizButton.setOnClickListener {
             showDialog()
         }*/
        authenticateUser(viewModel)
    }

    private fun authenticateUser(viewModel: IntroViewModel) {
        binding.introStartQuizButton.setOnClickListener {
            if (binding.inputNameEditText.text.toString().isNotEmpty()) {
                val currentUser = binding.inputNameEditText.text.toString()
                viewModel.authenticateUser(PresentationUserModel(userName = currentUser))
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.insert_username),
                    Toast.LENGTH_SHORT
                ).show()
            }
            observeResource(viewModel)
        }
    }

    private fun observeResource(viewModel: IntroViewModel) {
        viewLifecycleOwner.collectInLifecycleScope(viewModel.statusFlow) { resource ->
            when (resource) {
                is Resource.Success -> {
                    navigateTo(R.id.action_introFragment_to_homeFragment)
                }
                is Resource.Error -> {
                    binding.inputNameEditText.error = getString(resource.errorMessage)

                }
                else -> {
                    // Toast.makeText(requireContext(), "გეეეყყყოოოფფფააა", Toast.LENGTH_SHORT).show()
                }
            }
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