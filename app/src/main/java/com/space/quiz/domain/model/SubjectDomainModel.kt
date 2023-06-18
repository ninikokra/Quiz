package com.space.quiz.domain.model

data class SubjectDomainModel(
    val id: Int,
    val quizIcon: String?,
    val questionsCount: Int?,
    val quizDescription: String?,
    val quizTitle: String?
)