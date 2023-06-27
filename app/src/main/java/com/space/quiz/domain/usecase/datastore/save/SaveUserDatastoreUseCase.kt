package com.space.quiz.domain.usecase.datastore.save

import com.space.quiz.domain.repository.UserRepository
import com.space.quiz.domain.usecase.base.BaseUseCase

class SaveUserDatastoreUseCase(private val userRepository: UserRepository) :
    BaseUseCase<String, Unit>() {
    override suspend fun invoke(params: String?) {
        params?.let { username ->
            userRepository.saveUserName(username)
        }
    }
}