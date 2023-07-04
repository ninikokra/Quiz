package com.space.quiz.presentation.ui_intro.vm

import com.space.quiz.domain.usecase.datastore.get.GetUserDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.save.SaveUserDatastoreUseCase
import com.space.quiz.domain.usecase.save_user.SaveUserUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.UserUIModel
import com.space.quiz.presentation.model.mapper.user.UserUIDomainMapper
import com.space.quiz.presentation.ui_intro.ui.IntroFragmentDirections
import com.space.quiz.utils.Resource
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IntroViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val readDataStoreUseCase: GetUserDatastoreUseCase,
    private val saveDataStoreUseCase: SaveUserDatastoreUseCase,
    private val userUIToDomainMapper: UserUIDomainMapper
) : BaseViewModel() {
    private val _errorFlow = MutableStateFlow<Int?>(null)
    val errorFlow: StateFlow<Int?> = _errorFlow

    fun authenticateUser(user: UserUIModel) {
        viewModelScope {
            when (val status = saveUserUseCase.invoke(userUIToDomainMapper(user))) {
                is Resource.Success -> {
                    val domainUser = userUIToDomainMapper(user)
                    saveDataStoreUseCase.invoke(domainUser.userName)
                    navigateTo()
                }
                is Resource.Error -> {
                    _errorFlow.value = (status.errorMessage)
                }
            }
        }
    }

    fun getCurrentUserSession() {
        viewModelScope {
            val sessionResult = readDataStoreUseCase.invoke()
            if (sessionResult.isSuccess) {
                val session = sessionResult.getOrNull()
                if (!session.isNullOrEmpty()) {
                    navigateTo()
                }
            }
        }
    }

    private fun navigateTo() {
        navigate(IntroFragmentDirections.actionIntroFragmentToHomeFragment())
    }


}