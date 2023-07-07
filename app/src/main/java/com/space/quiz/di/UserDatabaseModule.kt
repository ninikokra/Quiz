package com.space.quiz.di

import android.content.Context
import androidx.room.Room
import com.space.quiz.data.local.database.UserDatabase
import org.koin.dsl.module

private  fun provideUserDatabase(context: Context): UserDatabase {
    return Room.databaseBuilder(context, UserDatabase::class.java, "database_1")
        .fallbackToDestructiveMigration().build()

}
private fun provideDao(db: UserDatabase) = db.userDao()
private fun provideUserDetailsDao(db: UserDatabase) = db.userDetailsDao()

val dbModule = module {
    single { provideUserDatabase(get()) }
    single { provideDao(get()) }
    single { provideUserDetailsDao(get()) }
}