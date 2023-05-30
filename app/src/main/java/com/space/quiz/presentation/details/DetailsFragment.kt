package com.space.quiz.presentation.details

import com.space.quiz.databinding.FragmentDetailsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun inflate(): Inflater<FragmentDetailsBinding> {
        return FragmentDetailsBinding::inflate
    }

    override fun onBind() {
    }
}