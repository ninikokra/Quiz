package com.space.quiz.presentation.ui_home.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_home.adapter.HomeAdapter
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.presentation.ui_home.vm.HomeViewModel
import com.space.quiz.utils.network.ResponseHandler
import com.space.quiz.utils.isVisible
import com.space.quiz.utils.lifecycleScope
import com.space.quiz.utils.observe
import com.space.quiz.utils.showToast
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun inflate(): Inflater<FragmentHomeBinding> {
        return FragmentHomeBinding::inflate
    }

    private val homeAdapter by lazy {
        HomeAdapter()
    }

    override fun onBind() {
        binding.logOutButton.setOnClickListener {
            showDialog()
        }
        observeUsername()
        initRecycler()
        setSubjectItemClickListener()
        fetchSubjects()
    }

    private fun setSubjectItemClickListener() {
        homeAdapter.setOnItemClickListener {
            viewModel.navigateToQuestions(it.quizTitle.toString())
        }
    }

    private fun initRecycler() {
        binding.subjectsRecyclerView.apply {
            adapter = homeAdapter
        }
        lifecycleScope {
            viewModel.fetchSubjects()
        }
    }

    private fun fetchSubjects() {
        lifecycleScope {
            viewModel.subjects.collect {
                homeAdapter.submitList(it)
            }
        }
        lifecycleScope {
            viewModel.isLoading.collect { isLoading ->
                binding.homeProgressBar.isVisible(isLoading)
            }
        }
        lifecycleScope {
            viewModel.error.collect { error ->
                error?.let {
                    requireContext().showToast(getString(R.string.service_error_text))
                }

            }
        }
    }

    private fun observeUsername() {
        viewModel.username.observe(viewLifecycleOwner) { username ->
            binding.helloUserTextView.text = getString(R.string.hello_user_text, username)
        }
        viewModel.fetchUsername()
    }

    private fun showDialog() {
        CustomDialogView(requireContext()).apply {
            showExitState()
            setPositiveButtonClickListener {
                viewModel.logout()
            }
            showDialog()
        }
    }
}