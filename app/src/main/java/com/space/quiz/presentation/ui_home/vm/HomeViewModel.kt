package com.space.quiz.presentation.ui_home.vm

import android.util.Log
import com.space.quiz.R
import com.space.quiz.domain.usecase.datastore.clear.ClearUserUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.subject.GetSubjectUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.SubjectUIModel
import com.space.quiz.presentation.model.mapper.subject.SubjectDomainUiMapper
import com.space.quiz.presentation.ui_home.ui.HomeFragmentDirections
import com.space.quiz.utils.network.ResponseHandler
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val readDatastoreUseCase: ReadDatastoreUseCase,
    private val clearDatastoreUseCase: ClearUserUseCase,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val subjectDomainUiMapper: SubjectDomainUiMapper
) : BaseViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    private val _subjectList =
        MutableStateFlow<ResponseHandler<List<SubjectUIModel>>>(ResponseHandler.Loading())
    val subjectList = _subjectList.asStateFlow()

    fun fetchSubjectList() {
        viewModelScope {
            _subjectList.value = ResponseHandler.Loading()
            getSubjectUseCase.execute().collect { response ->
                _subjectList.value = when (response) {
                    is ResponseHandler.Success -> {
                        val subjectList = response.response.map { subjectDomainModel ->
                            subjectDomainUiMapper(subjectDomainModel)
                        }
                        ResponseHandler.Success(subjectList)
                    }
                    is ResponseHandler.Error -> {
                        ResponseHandler.Error(R.string.service_error_text)
                    }
                    is ResponseHandler.Loading -> {
                        ResponseHandler.Loading()
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

    fun navigateToTests() {
        navigate(HomeFragmentDirections.actionHomeFragmentToTestFragment())
    }

    private fun navigateToIntro() {
        navigate(HomeFragmentDirections.actionHomeFragmentToIntroFragment())
    }
}
