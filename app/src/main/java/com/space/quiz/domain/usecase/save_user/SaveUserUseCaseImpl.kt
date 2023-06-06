package com.space.quiz.domain.usecase.save_user

import com.space.quiz.domain.model.DomainUserModel
import com.space.quiz.domain.repository.UserRepository
import com.space.quiz.utils.isUserNameCorrect

class SaveUserUseCaseImpl (private val userRepository: UserRepository):SaveUserUseCase{
    override suspend fun saveUser(user: DomainUserModel): Boolean {
        if (!isUserNameCorrect(user.userName)){
            return false
        }
        val currentUser = userRepository.getUserName(user.userName)
        if (currentUser!=null){
            return true
        }
        userRepository.saveUser(user)
        return true
    }
}