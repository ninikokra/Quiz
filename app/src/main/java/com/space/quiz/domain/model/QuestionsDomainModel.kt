package com.space.quiz.domain.model

data class QuestionsDomainModel(
    val answers: List<String>,
    val correctAnswer: String,
    val questionIndex: Int,
    val questionTitle: String,
    val subjectId: Int
)