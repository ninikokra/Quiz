package com.space.quiz.presentation.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.quiz.R
import com.space.quiz.databinding.FragmentTestsBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater

class TestsFragment : BaseFragment<FragmentTestsBinding>() {
    override fun inflate(): Inflater<FragmentTestsBinding> {
        return FragmentTestsBinding::inflate
    }

    override fun onBind() {
    }
}