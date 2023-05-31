package com.space.quiz.presentation.details

import androidx.navigation.fragment.findNavController
import com.space.quiz.R
import com.space.quiz.databinding.FragmentDetailsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.views.CustomTitleView

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun inflate(): Inflater<FragmentDetailsBinding> {
        return FragmentDetailsBinding::inflate
    }

    override fun onBind() {
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