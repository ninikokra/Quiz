package com.space.quiz.data.repository

import com.space.quiz.data.local.dao.UserDao
import com.space.quiz.data.local.mapper.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.UserEntityToDomainMapper
import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val entityToDomain: UserEntityToDomainMapper,
    private val domainToEntity: UserDomainToEntityMapper
) : UserRepository{
    override suspend fun saveUser(user: UserDomainModel) {
        userDao.saveUser(domainToEntity(user))
    }

    override suspend fun getUserName(userName: String): UserDomainModel? {
       return userDao.getUserName(userName)?.let { entityToDomain(it) }
    }
}