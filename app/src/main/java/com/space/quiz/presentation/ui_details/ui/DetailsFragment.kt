package com.space.quiz.presentation.ui_details.ui

import androidx.navigation.fragment.findNavController
import com.space.quiz.databinding.FragmentDetailsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_details.vm.DetailsViewModel
import com.space.quiz.presentation.views.HeaderView
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>() {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    override fun inflate(): Inflater<FragmentDetailsBinding> {
        return FragmentDetailsBinding::inflate
    }

    override fun onBind() {
        showTitleVIew()
    }

    private fun showTitleVIew() {
        HeaderView(requireContext()).apply {
            binding.root.addView(this)
            collectedPointsToolBar()
            setBackButton {
                findNavController().popBackStack()
            }
        }
    }
}