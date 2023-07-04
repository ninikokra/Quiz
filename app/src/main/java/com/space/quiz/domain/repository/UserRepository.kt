package com.space.quiz.domain.repository

import com.space.quiz.domain.model.UserDomainModel

interface UserRepository {
    suspend fun saveUser(user: UserDomainModel)
    suspend fun getUserName(userName:String): UserDomainModel?

    suspend fun saveUserName(username: String)
    suspend fun getUser(): Result<String>
    suspend fun clear()
}