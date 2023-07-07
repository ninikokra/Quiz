package com.space.quiz.domain.usecase.user_points

import com.space.quiz.domain.model.UserDetailsDomainModel
import com.space.quiz.domain.repository.UserDetailsRepository
import com.space.quiz.domain.usecase.base.BaseUseCase
import com.space.quiz.domain.usecase.gpa.GpaUseCase

class InsertUserPointUseCase(
    private val userDetailsRepository: UserDetailsRepository,
    private val gpaUseCase: GpaUseCase
) : BaseUseCase<UserDetailsDomainModel, Unit>() {
    override suspend fun invoke(params: UserDetailsDomainModel?) {
            userDetailsRepository.insertUserPoints(params!!)
            gpaUseCase(params.userName)
    }
}
