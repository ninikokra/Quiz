package com.space.quiz.domain.usecase.save_user

import com.space.quiz.R
import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.domain.repository.UserRepository
import com.space.quiz.domain.usecase.base.BaseUseCase
import com.space.quiz.utils.Resource
import com.space.quiz.utils.UserValidationRegex.isValidUsername

class SaveUserUseCaseImpl(private val userRepository: UserRepository) :
    BaseUseCase<UserDomainModel, Resource>() {
    override suspend fun invoke(params: UserDomainModel?): Resource {
        if (!isValidUsername(params!!.userName)) {
            return Resource.Error(R.string.invalid_username_text)
        }
        val currentUser = userRepository.getUserName(params.userName)
        if (currentUser != null) {
            return Resource.Success
        }
        userRepository.saveUser(params)
        return Resource.Success
    }
}

