package com.space.quiz.data.repository

import com.space.quiz.data.local.dao.UserDao
import com.space.quiz.data.local.datastore.UserDataStore
import com.space.quiz.data.local.mapper.user.UserDomainToEntityMapper
import com.space.quiz.data.local.mapper.user.UserEntityToDomainMapper
import com.space.quiz.domain.model.UserDomainModel
import com.space.quiz.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userDataStore: UserDataStore,
    private val entityToDomain: UserEntityToDomainMapper,
    private val domainToEntity: UserDomainToEntityMapper
) : UserRepository{
    override suspend fun saveUser(user: UserDomainModel) {
        userDao.saveUser(domainToEntity(user))
    }

    override suspend fun getUserName(userName: String): UserDomainModel? {
        return userDao.getUserName(userName)?.let { entityToDomain(it) }
    }

    override suspend fun save(username: String) {
        Result.runCatching {
            userDataStore.save("", username)
        }
    }


    override suspend fun read(): Result<String> {
        return Result.runCatching {
            val flow = userDataStore.read("")
            flow.firstOrNull() ?: ""
        }
    }

    override suspend fun clear() {
        Result.runCatching {
            userDataStore.clear("")
        }
    }


}