package com.space.quiz.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore(private val dataStore: DataStore<Preferences>) {

    suspend fun save(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun read(key: String, value: String = ""): Flow<String> {
        val preferences = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: value
        }
        return preferences
    }

    suspend fun clear(key: String) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }
}