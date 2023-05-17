package com.space.quiz.presentation.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.databinding.FragmentIntroBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.home.HomeFragment

class IntroFragment : BaseFragment<FragmentIntroBinding>() {

    override fun inflate(): Inflater<FragmentIntroBinding> {
        return FragmentIntroBinding::inflate
    }
    override fun onBind() {
        binding.introStartQuizButton.setOnClickListener {
            val home = HomeFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainContainer,home)?.addToBackStack(null)
                ?.commit()
        }
    }

}