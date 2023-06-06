package com.space.quiz.presentation.fragment_details.ui

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.space.quiz.databinding.FragmentDetailsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.fragment_details.vm.DetailsViewModel
import com.space.quiz.presentation.fragment_intro.vm.IntroViewModel
import com.space.quiz.presentation.views.CustomTitleView
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>() {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    override fun inflate(): Inflater<FragmentDetailsBinding> {
        return FragmentDetailsBinding::inflate
    }

    override fun onBind(viewModel: DetailsViewModel) {
        showTitleVIew()
    }

    private fun showTitleVIew() {
        val titleView = CustomTitleView(requireContext())
        binding.root.addView(titleView)
        titleView.collectedPointsToolBar()
        titleView.setBackButton {
            findNavController().popBackStack()
        }
    }
}