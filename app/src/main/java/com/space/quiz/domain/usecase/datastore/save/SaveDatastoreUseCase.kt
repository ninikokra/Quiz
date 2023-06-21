package com.space.quiz.domain.usecase.datastore.save

import com.space.quiz.domain.repository.UserRepository


interface SaveDatastoreUseCase {
    suspend fun save(username: String)
}
class SaveDatastoreUseCaseImpl(private val userRepository: UserRepository) :
    SaveDatastoreUseCase {
    override suspend fun save(username: String) {
        userRepository.save(username)
    }
}

//TODO working about baseUseCase..
/*class SaveDatastoreUseCase(private val userRepository: UserRepository) :
    BaseUseCase<String, Unit>() {
    override suspend fun invoke(params: String?) {
        params?.let { username ->
            userRepository.save(username)
        }
    }
}*/