package com.space.quiz.domain.repository

import com.space.quiz.domain.model.UserDetailsDomainModel

interface UserDetailsRepository {
    suspend fun insertUserPoints(userDetailsDomain: UserDetailsDomainModel)

    suspend fun getAllStats(username:String): List<UserDetailsDomainModel>


}