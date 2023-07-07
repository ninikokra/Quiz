package com.space.quiz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_details")
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quizDescription: String,
    val quizIcon: String,
    val quizTitle: String,
    val collectedPoints: Int,
    val userName: String,
    val questionsCount: Int,
)
