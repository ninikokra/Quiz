package com.space.quiz.presentation.model.mapper

data class UserDetailsUIModel(
    val id: Int,
    val quizDescription: String,
    val quizIcon: String,
    val quizTitle: String,
    val collectedPoints: Int,
    val userName: String,
    val questionsCount: Int,
)
