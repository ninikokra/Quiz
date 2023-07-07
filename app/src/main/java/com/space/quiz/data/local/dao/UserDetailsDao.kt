package com.space.quiz.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.space.quiz.data.local.entity.UserDetailsEntity

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM user_details WHERE userName = :userName AND quizTitle = :quizTitle")
    suspend fun getUserPoints(userName: String, quizTitle: String): UserDetailsEntity?

    @Query("SELECT * FROM user_details WHERE userName = :userName")
    suspend fun getAllStats(userName: String): List<UserDetailsEntity>

    @Insert
    suspend fun insertUserPoints(userPoints: UserDetailsEntity)

    @Delete
    suspend fun updatePoints(userDetails: UserDetailsEntity)
}