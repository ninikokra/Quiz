package com.space.quiz.domain.usecase.datastore.clear

import com.space.quiz.domain.repository.UserRepository
import com.space.quiz.domain.usecase.base.BaseUseCase



interface ClearUserUseCase {
    suspend fun invoke()
}

class ClearUserUseCaseImpl(private val userRepository: UserRepository) : ClearUserUseCase {
    override suspend fun invoke() {
        userRepository.clear()
    }
}

//TODO working about baseUseCase..
/*
class ClearDatastoreUseCase(private val userRepository: UserRepository) :
    BaseUseCase<Unit, Result<Unit>>() {

    override suspend fun invoke(params: Unit?): Result<Unit> {
        return try {
            userRepository.clear()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

*/




