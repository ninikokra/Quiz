package com.space.quiz.presentation.model

data class QuestionsUIModel(
    val answers: List<String>,
    val correctAnswer: String,
    val questionIndex: Int,
    val questionTitle: String,
    val subjectId: Int
)