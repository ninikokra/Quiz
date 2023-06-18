package com.space.quiz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val quizTitle: String?,
    val quizIcon: String?,
    val questionsCount: Int?,
    val quizDescription: String?,
)