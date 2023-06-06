package com.space.quiz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quiz.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE userName = :username")
    suspend fun getUserName(username: String): UserEntity?
}