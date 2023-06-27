package com.space.quiz.presentation.ui_home.vm

import com.space.quiz.domain.usecase.datastore.clear.ClearDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
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

class HomeViewModel(
    private val readDatastoreUseCase: ReadDatastoreUseCase,
    private val clearDatastoreUseCase: ClearDatastoreUseCase,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val subjectDomainUiMapper: SubjectDomainUiMapper
) : BaseViewModel() {
    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    private val _subjects = MutableStateFlow<List<SubjectUIModel>>(emptyList())
    val subjects: StateFlow<List<SubjectUIModel>> = _subjects

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


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
            }
        }
    }

    fun logout() {
        viewModelScope {
            clearDatastoreUseCase.invoke()
            navigateToIntro()
        }
    }

    fun navigateToQuestions(subject: String) {
        navigate(HomeFragmentDirections.actionHomeFragmentToQuestionsFragment(subject))
    }


    private fun navigateToIntro() {
        navigate(HomeFragmentDirections.actionHomeFragmentToIntroFragment())
    }
}
