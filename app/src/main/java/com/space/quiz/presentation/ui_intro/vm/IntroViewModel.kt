package com.space.quiz.presentation.ui_intro.vm

import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.domain.usecase.base.BaseUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.save.SaveDatastoreUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.UserUIModel
import com.space.quiz.presentation.model.mapper.user.UserUIDomainMapper
import com.space.quiz.presentation.ui_intro.ui.IntroFragmentDirections
import com.space.quiz.utils.Resource
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IntroViewModel(
    private val saveUserUseCase: BaseUseCase<UserDomainModel, Resource>,
    private val readDataStoreUseCase: ReadDatastoreUseCase,
    private val saveDataStoreUseCase: SaveDatastoreUseCase,
    private val userUIToDomainMapper: UserUIDomainMapper
) : BaseViewModel() {
    private val _errorFlow = MutableStateFlow(0)
    val errorFlow: StateFlow<Int> = _errorFlow

    fun authenticateUser(user: UserUIModel) {
        viewModelScope {
            when (val status = saveUserUseCase.invoke(userUIToDomainMapper(user))) {
                is Resource.Success -> {
                    val domainUser = userUIToDomainMapper(user)
                    saveDataStoreUseCase.save(domainUser.userName)
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