package com.space.quiz.data.repository

import com.space.quiz.data.local.dao.UserDetailsDao
import com.space.quiz.data.local.mapper.quiz_detail.UserDetailsDomainEntityMapper
import com.space.quiz.domain.model.UserDetailsDomainModel
import com.space.quiz.domain.model.mapper.UserDetailsEntityDomainMapper
import com.space.quiz.domain.repository.UserDetailsRepository

class UserDetailsRepositoryImpl(
    private val userDetailsDao: UserDetailsDao,
    private val userDomainToEntityMapper: UserDetailsDomainEntityMapper,
    private val userEntityToDomainMapper: UserDetailsEntityDomainMapper
) : UserDetailsRepository{
    override suspend fun insertUserPoints(userDetailsDomain: UserDetailsDomainModel) {
        val userPoints = userDetailsDao.getUserPoints(
            userDetailsDomain.userName,
            userDetailsDomain.quizTitle
        )
        userPoints?.let {
            if (it.collectedPoints< userDetailsDomain.collectedPoints){
                userDetailsDao.updatePoints(it)
                userDetailsDao.insertUserPoints(userDomainToEntityMapper(userDetailsDomain))
            }
            return
        }
        userDetailsDao.insertUserPoints(userDomainToEntityMapper(userDetailsDomain))
    }

    override suspend fun getAllStats(username: String): List<UserDetailsDomainModel> {
         return userDetailsDao.getAllStats(username).map {
             userEntityToDomainMapper(it)
         }
    }
}
