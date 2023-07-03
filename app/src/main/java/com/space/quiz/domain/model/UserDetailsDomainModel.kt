package com.space.quiz.domain.model

data class UserDetailsDomainModel(
    val id: Int,
    val quizDescription: String,
    val quizIcon: String,
    val quizTitle: String,
    val collectedPoints: Int,
    val userName: String,
    val questionsCount: Int,
)
