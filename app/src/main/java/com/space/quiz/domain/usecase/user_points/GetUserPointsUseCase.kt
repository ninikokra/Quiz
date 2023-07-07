package com.space.quiz.domain.usecase.user_points

import com.space.quiz.domain.model.UserDetailsDomainModel
import com.space.quiz.domain.repository.UserDetailsRepository
import com.space.quiz.domain.usecase.base.BaseUseCase

class GetUserPointsUseCase(private val userDetailsRepository: UserDetailsRepository) :
    BaseUseCase<String, List<UserDetailsDomainModel>>() {
    override suspend fun invoke(params: String?): List<UserDetailsDomainModel> {
        return userDetailsRepository.getAllStats(params!!)
    }
}