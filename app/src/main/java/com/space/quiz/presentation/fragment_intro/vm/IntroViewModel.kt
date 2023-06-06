package com.space.quiz.presentation.fragment_intro.vm

import androidx.lifecycle.ViewModel
import com.space.quiz.R
import com.space.quiz.domain.usecase.save_user.SaveUserUseCase
import com.space.quiz.presentation.model.PresentationUserModel
import com.space.quiz.presentation.model.mapper.UserPresentationToDomainMapper
import com.space.quiz.utils.Resource
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IntroViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val userPresentationToDomain : UserPresentationToDomainMapper
): ViewModel() {
    private val _statusFlow = MutableStateFlow<Resource?>(null)
    val statusFlow: StateFlow<Resource?> = _statusFlow

    fun authenticateUser(user: PresentationUserModel){
        viewModelScope {
            val isCorrect = saveUserUseCase.saveUser(userPresentationToDomain(user))
            _statusFlow.value =if (isCorrect){
                Resource.Success
            }else{
                Resource.Error(R.string.invalid_username_text)
            }
        }
    }
}