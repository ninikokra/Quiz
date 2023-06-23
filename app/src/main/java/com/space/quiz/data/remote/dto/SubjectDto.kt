package com.space.quiz.data.remote.dto

data class SubjectDto(
    val id: Int,
    val questions: List<QuestionDto>,
    val questionsCount: Int,
    val quizDescription: String,
    val quizIcon: String?,
    val quizTitle: String
)