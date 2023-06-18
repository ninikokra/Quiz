package com.space.quiz.data.remote.dto

data class QuestionDto(
    val answers: List<String>,
    val correctAnswer: String,
    val questionIndex: Int,
    val questionTitle: String,
    val subjectId: Int
)
