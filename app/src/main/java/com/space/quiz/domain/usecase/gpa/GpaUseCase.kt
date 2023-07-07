package com.space.quiz.domain.usecase.gpa

import com.space.quiz.domain.repository.UserRepository
import com.space.quiz.domain.usecase.base.BaseUseCase
import com.space.quiz.domain.usecase.user_points.GetUserPointsUseCase

class GpaUseCase(
    private val userRepository: UserRepository,
    private val getUserPointsUseCase: GetUserPointsUseCase
) : BaseUseCase<String, Float>() {
    override suspend operator fun invoke(params: String?): Float {
        if(params==null){
            return 0.0f
        }
        val subjectPointPercent = mutableListOf<Float>()

        getUserPointsUseCase.invoke(params).forEach {
            subjectPointPercent.add(it.collectedPoints.toFloat() / it.questionsCount)
        }

        val gpa = calculateGpa(subjectPointPercent)
        if (gpa!=null){
            userRepository.updateGpa(params, gpa)
        }
        return gpa ?: 0.0f
    }

    private fun calculateGpa(subjectPointPercent: List<Float>): Float? {
        if (subjectPointPercent.isEmpty()) {
            return null
        }
        val averagePercentage = subjectPointPercent.average()
        return (averagePercentage * 4).toFloat()
    }
}
