package com.space.quiz.presentation.ui_home.ui

import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.ui_home.adapter.HomeAdapter
import com.space.quiz.presentation.views.dialog.CustomDialogView
import com.space.quiz.presentation.ui_home.vm.HomeViewModel
import com.space.quiz.utils.*
import kotlinx.coroutines.flow.combine
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
        setListener()
        observeUsername()
        initRecycler()
        setSubjectItemClickListener()
        fetchSubjects()
        backButton()
        setupSearchView()
    }

    private fun setSubjectItemClickListener() {
        homeAdapter.setOnItemClickListener {
            viewModel.navigateToQuestions(it.quizTitle.toString(), it)
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
            lifecycleScope {
                viewModel.subjects.combine(viewModel.searchQuery) { subjects, query ->
                    if (query.isEmpty()) {
                        subjects
                    } else {
                        subjects.filter { subject ->
                            subject.quizTitle.contains(query, ignoreCase = true)
                        }
                    }
                }.collect { filteredSubjects ->
                    homeAdapter.submitList(filteredSubjects)
                }
            }
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
        viewModel.gpa.observe(viewLifecycleOwner) { gpa ->
            val gpaText = getString(R.string.gpa_text)
            val getGpaFormatText = String.format(getString(R.string.gpa_format), gpa)
            binding.userGPATextView.setColoredText(
                gpaText,
                getGpaFormatText,
                ContextCompat.getColor(requireContext(), R.color.yellow_primary)
            )
        }
        viewModel.fetchUsername()
    }

    private fun setListener() {
        binding.detailsRectangle.setOnClickListener {
            viewModel.navigateToDetails()
        }
        binding.logOutButton.setOnClickListener {
            showDialog()
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.setSearchQuery(newText)
                return true
            }
        })
    }

    private fun showDialog() {
        CustomDialogView(requireContext()).apply {
            showExitState()
            setPositiveButtonClickListener {
                viewModel.logout()
            }
            setNegativeButtonClickListener {}
            showDialog()
        }
    }

    private fun backButton() {
        requireActivity().onBackPressedDispatcher.addCallback {
            showDialog()
        }
    }
}