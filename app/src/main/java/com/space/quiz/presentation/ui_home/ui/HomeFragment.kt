package com.space.quiz.presentation.ui_home.ui

import HomeAdapter
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.model.mapper.SubjectUIModel
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.presentation.ui_home.vm.HomeViewModel
import com.space.quiz.utils.isVisible
import com.space.quiz.utils.lifecycleScope
import com.space.quiz.utils.observe
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
        observer(viewModel)
        setItemCLickRecyclerView(viewModel)
    }


    private fun setItemCLickRecyclerView(viewModel: HomeViewModel) {
        homeAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClick(item: SubjectUIModel) {
                viewModel.navigateToTests()
            }
        })
    }

    private fun initRecycler(viewModel: HomeViewModel) {
        binding.subjectsRecyclerView.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        lifecycleScope {
            viewModel.getQuizQuestions()

        }
    }

    private fun observer(viewModel: HomeViewModel) {
        lifecycleScope {
            viewModel.success.collect {
                homeAdapter.submitList(it)
            }
        }
        lifecycleScope {
            viewModel.isLoading.collect { isLoading ->
                binding.homeProgressBar.isVisible(isLoading)
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

    private fun toDetailsFragment() {
        binding.detailsTextViewButton.setOnClickListener {
            //navigateTo(R.id.action_homeFragment_to_detailsFragment)
        }
    }

    private fun toQuestionsFragment() {
        binding.chooseSubjectTextView.setOnClickListener {
            //navigateTo(R.id.action_homeFragment_to_testFragment)
        }
    }
}