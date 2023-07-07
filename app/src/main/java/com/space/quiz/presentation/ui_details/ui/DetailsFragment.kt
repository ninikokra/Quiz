package com.space.quiz.presentation.ui_details.ui


import androidx.activity.addCallback
import androidx.core.content.ContentProviderCompat.requireContext
import com.space.quiz.databinding.FragmentDetailsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_details.adapter.DetailsAdapter
import com.space.quiz.presentation.ui_details.vm.DetailsViewModel
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.utils.isVisible
import com.space.quiz.utils.lifecycleScope
import kotlin.reflect.KClass

class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>() {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    private val detailsAdapter by lazy {
        DetailsAdapter()
    }

    override fun inflate(): Inflater<FragmentDetailsBinding> {
        return FragmentDetailsBinding::inflate
    }

    override fun onBind() {
        viewModel.getCurrentUser()
        setUpRecycler()
        pointsObserver()
        setLogout()
        backButton()
    }

    private fun setUpRecycler() {
        binding.detailsRecyclerView.apply {
            adapter = detailsAdapter
        }
        lifecycleScope {
            viewModel.getUserSubject()
        }
    }

    private fun pointsObserver() {
        lifecycleScope {
            viewModel.subjectsItem.collect {
                if (it.isEmpty()) {
                    binding.noResultPointsTextView.isVisible(true)
                } else {
                    binding.detailsRecyclerView.isVisible(true)
                    detailsAdapter.submitList(it)
                    binding.noResultPointsTextView.isVisible(false)
                }
            }
        }
    }
    fun setLogout(){
        binding.logOutButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        CustomDialogView(requireContext()).apply {
            showExitState()
            setPositiveButtonClickListener {
                viewModel.logout()
            }
            setNegativeButtonClickListener {}
            showDialog()
        }
    }
    private fun backButton(){
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.navigateToHome()
        }
    }
}