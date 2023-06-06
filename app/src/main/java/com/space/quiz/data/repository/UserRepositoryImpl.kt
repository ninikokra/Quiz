package com.space.quiz.data.repository

import com.space.quiz.data.local.dao.UserDao
import com.space.quiz.data.local.mapper.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.UserEntityToDomainMapper
import com.space.quiz.domain.model.DomainUserModel
import com.space.quiz.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val entityToDomain: UserEntityToDomainMapper,
    private val domainToEntity: UserDomainToEntityMapper
) : UserRepository{
    override suspend fun saveUser(user: DomainUserModel) {
        userDao.saveUser(domainToEntity(user))
    }

    override suspend fun getUserName(userName: String): DomainUserModel? {
       return userDao.getUserName(userName)?.let { entityToDomain(it) }
    }
}