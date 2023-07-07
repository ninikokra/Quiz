package com.space.quiz.domain.usecase.datastore.get

import com.space.quiz.domain.repository.UserRepository
import com.space.quiz.domain.usecase.base.BaseUseCase


class GetUserDatastoreUseCase(private val userRepository: UserRepository) :
    BaseUseCase<Unit, Result<String>>() {
    override suspend fun invoke(params: Unit?):Result<String> {
        return userRepository.getUser()
    }
}

