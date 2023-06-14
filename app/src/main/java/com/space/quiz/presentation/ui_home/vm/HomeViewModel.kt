package com.space.quiz.presentation.ui_home.vm

import com.space.quiz.domain.usecase.datastore.clear.ClearUserUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.subject.GetSubjectUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.mapper.SubjectUIModel
import com.space.quiz.presentation.model.mapper.subject.SubjectDomainUiMapper
import com.space.quiz.presentation.ui_home.ui.HomeFragmentDirections
import com.space.quiz.utils.ResponseHandler
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val readDatastoreUseCase: ReadDatastoreUseCase,
    private val clearDatastoreUseCase: ClearUserUseCase,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val subjectDomainToUiMapper: SubjectDomainUiMapper
) : BaseViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    private val _success = MutableStateFlow<List<SubjectUIModel>>(emptyList())
    val success: StateFlow<List<SubjectUIModel>> = _success

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun getQuizQuestions() {
        viewModelScope {
            _isLoading.value = true
            getSubjectUseCase.execute()
                .onEach { responseHandler ->
                    when (responseHandler) {
                        is ResponseHandler.Success -> {
                            val quizItems =
                                responseHandler.response.map { subjectDomainToUiMapper(it) }
                            _success.value = quizItems
                            _isLoading.value = false
                        }
                        is ResponseHandler.Error -> {
                            _error.value = responseHandler.errorResponse
                            _isLoading.value = false
                        }
                        is ResponseHandler.InProcess -> {
                            _isLoading.value = true
                        }
                    }
                }
                .catch { exception ->
                    _isLoading.value = false
                    _error.value = exception.message
                }
                .collect()
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

     fun navigateToTests() {
        navigate(HomeFragmentDirections.actionHomeFragmentToTestFragment())
    }

    private fun navigateToIntro() {
        navigate(HomeFragmentDirections.actionHomeFragmentToIntroFragment())
    }
}
