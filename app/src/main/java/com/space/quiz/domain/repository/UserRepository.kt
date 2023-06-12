package com.space.quiz.domain.repository

import com.space.quiz.domain.model.UserDomainModel

interface UserRepository {
    suspend fun saveUser(user: UserDomainModel)
    suspend fun getUserName(userName:String): UserDomainModel?
}