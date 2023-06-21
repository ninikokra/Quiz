package com.space.quiz.presentation.ui_home.vm

import com.space.quiz.domain.usecase.datastore.clear.ClearUserUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.ui_home.ui.HomeFragmentDirections
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val readDatastoreUseCase: ReadDatastoreUseCase,
    private val clearDatastoreUseCase: ClearUserUseCase
) : BaseViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

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

    private fun navigateToIntro() {
        navigate(HomeFragmentDirections.actionHomeFragmentToIntroFragment())
    }
}
