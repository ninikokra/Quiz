package com.space.quiz.presentation.ui_home.vm

import android.util.Log
import com.space.quiz.domain.usecase.gpa.GpaUseCase
import com.space.quiz.domain.model.SubjectDomainModel
import com.space.quiz.domain.usecase.datastore.clear.ClearDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.get.GetUserDatastoreUseCase
import com.space.quiz.domain.usecase.subject.GetSubjectUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.SubjectUIModel
import com.space.quiz.presentation.model.mapper.subject.SubjectDomainUiMapper
import com.space.quiz.presentation.ui_home.ui.HomeFragmentDirections
import com.space.quiz.utils.network.ResponseHelper
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.*
import kotlin.math.log

class HomeViewModel(
    private val readDatastoreUseCase: GetUserDatastoreUseCase,
    private val clearDatastoreUseCase: ClearDatastoreUseCase,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val gpaUseCase: GpaUseCase,
    private val subjectDomainUiMapper: SubjectDomainUiMapper
) : BaseViewModel() {
    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    private val _subjects = MutableStateFlow<List<SubjectUIModel>>(emptyList())
    val subjects: StateFlow<List<SubjectUIModel>> = _subjects

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
  
    private val _gpa = MutableStateFlow<Float?>(null)
    val gpa: StateFlow<Float?> = _gpa

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun fetchSubjects() {
        viewModelScope {
            _isLoading.value = true
            getSubjectUseCase.invoke()
                .onStart { _isLoading.value = true }
                .onCompletion { _isLoading.value = false }
                .catch { exception ->
                    _isLoading.value = false
                    _error.value = exception.message
                }
                .collect { responseHandler ->
                    when (responseHandler) {
                        is ResponseHelper.Success -> {
                            val quizItems =
                                responseHandler.response.map { subjectDomainUiMapper(it) }
                            _subjects.value = quizItems
                        }
                        is ResponseHelper.Error -> {
                            _error.value = responseHandler.errorResponseString
                        }
                        is ResponseHelper.Loading -> {
                            _isLoading.value = responseHandler.isLoading
                        }
                    }
                }
        }
    }

    fun fetchUsername() {
        viewModelScope {
            val usernameResult = readDatastoreUseCase.invoke()
            if (usernameResult.isSuccess) {
                val username = usernameResult.getOrNull()
                _username.value = username
                username?.let {
                    _gpa.value = calculateGpa(it)

                }
            }
        }
    }

    private suspend fun calculateGpa(userName: String):Float {
        return gpaUseCase.invoke(userName)
    }

    fun logout() {
        viewModelScope {
            clearDatastoreUseCase.invoke()
            navigateToIntro()
        }
    }
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun navigateToQuestions(subject: String,item:SubjectUIModel) {
        navigate(HomeFragmentDirections.actionHomeFragmentToQuestionsFragment(subject,item))
    }

    private fun navigateToIntro() {
        navigate(HomeFragmentDirections.actionHomeFragmentToIntroFragment())
    }
     fun navigateToDetails() {
        navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
    }
}
