package com.dolearnn.cg.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val APP_PREFERENCES = "app_config"
val Context.appConfigDataStore: DataStore<Preferences> by preferencesDataStore(name = APP_PREFERENCES)

class AppConfig(private val preferencesDataStore: DataStore<Preferences>) {

    fun enquireFreshInstall(): Flow<Boolean> {
        return preferencesDataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[Keys.IS_FRESH_INSTALL] ?: true
        }
    }

    suspend fun setInstalled() {
        preferencesDataStore.edit { preferences ->
            preferences[Keys.IS_FRESH_INSTALL] = false
        }
    }

    private object Keys {
        private const val freshInstallKey = "is_fresh_install"
        val IS_FRESH_INSTALL = booleanPreferencesKey(freshInstallKey)
    }
}