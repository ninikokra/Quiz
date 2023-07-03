package com.space.quiz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quiz.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE userName = :userName")
    suspend fun getUserName(userName: String): UserEntity?


    @Query("UPDATE users SET gpa = :gpa WHERE username = :username")
    suspend fun updateGPA(username: String, gpa: Float)
}