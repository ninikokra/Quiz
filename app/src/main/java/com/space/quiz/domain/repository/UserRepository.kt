package com.space.quiz.domain.repository

import com.space.quiz.domain.model.DomainUserModel

interface UserRepository {
    suspend fun saveUser(user: DomainUserModel)
    suspend fun getUserName(userName:String): DomainUserModel?
}