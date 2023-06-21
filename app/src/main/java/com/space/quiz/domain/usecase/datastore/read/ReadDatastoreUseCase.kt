package com.space.quiz.domain.usecase.datastore.read

import com.space.quiz.domain.repository.UserRepository
import com.space.quiz.domain.usecase.base.BaseUseCase


interface ReadDatastoreUseCase {
    suspend fun invoke(): Result<String>
}

class ReadDatastoreUseCaseImpl(private val userRepository: UserRepository):ReadDatastoreUseCase{
    override suspend fun invoke(): Result<String> {
        return userRepository.read()
    }
}

//TODO working about baseUseCase..
/*class ReadDatastoreUseCase(private val userRepository: UserRepository) :
    BaseUseCase<Unit, Result<String>>() {
    override suspend fun invoke(params: Unit?):Result<String> {
        return userRepository.read()
    }
}*/

