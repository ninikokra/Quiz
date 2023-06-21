package com.space.quiz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quiz.data.local.dao.UserDao
import com.space.quiz.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 3,
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}