package com.space.quiz.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun inflate(): Inflater<FragmentHomeBinding> {
        return FragmentHomeBinding::inflate
    }

    override fun onBind() {
        binding.detailsTextViewButton.setOnClickListener {
            navigationToDetails()
        }
    }

    private fun navigationToTests() {
        findNavController().navigate(
            R.id.action_homeFragment_to_testFragment
        )
    }

    private fun navigationToDetails() {
        findNavController().navigate(
            R.id.action_homeFragment_to_detailsFragment
        )
    }
}