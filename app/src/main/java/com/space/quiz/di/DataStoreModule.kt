package com.space.quiz.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.space.quiz.data.local.datastore.UserDataStore
import org.koin.dsl.module

private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user_datastore_preference"
)

private fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.userDataStore
}

val userDataStore = module {
    single { provideDataStore(get()) }
    single { UserDataStore(get()) }
}