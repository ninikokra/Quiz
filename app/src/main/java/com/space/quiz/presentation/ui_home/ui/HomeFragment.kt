package com.space.quiz.presentation.ui_home.ui

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

    override fun onBind(viewModel: HomeViewModel) {
        binding.logOutButton.setOnClickListener {
            showDialog(viewModel)
        }
        observeUsername(viewModel)
        initRecycler(viewModel)
        setSubjectItemClickListener(viewModel)
        fetchSubjects(viewModel)
    }

    private fun setSubjectItemClickListener(viewModel: HomeViewModel) {
        homeAdapter.setOnItemClickListener {
            viewModel.navigateToTests()
        }
    }

    private fun initRecycler(viewModel: HomeViewModel) {
        binding.subjectsRecyclerView.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        lifecycleScope {
            viewModel.fetchSubjectList()
        }
    }

    private fun fetchSubjects(viewModel: HomeViewModel) {
        viewModel.subjectList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResponseHandler.Success -> {
                    val subjectList = response.response
                    homeAdapter.submitList(subjectList)
                    binding.homeProgressBar.isVisible(false)
                }
                is ResponseHandler.Error -> {
                    requireContext().showToast(getString(R.string.service_error_text))
                }
                is ResponseHandler.Loading -> {
                    binding.homeProgressBar.isVisible(true)
                }
            }
        }
    }

    private fun observeUsername(viewModel: HomeViewModel) {
        viewModel.username.observe(viewLifecycleOwner) { username ->
            binding.helloUserTextView.text = getString(R.string.hello_user_text, username)
        }
        viewModel.fetchUsername()
    }

    private fun showDialog(viewModel: HomeViewModel) {
        val customDialogView = CustomDialogView(requireContext())
        customDialogView.showExitState()

        customDialogView.setPositiveButtonClickListener {
            viewModel.logout()
        }
        customDialogView.setNegativeButtonClickListener {}
        customDialogView.showDialog()
    }
}