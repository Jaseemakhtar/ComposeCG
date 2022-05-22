package com.dolearnn.cg.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.dolearnn.cg.data.datastore.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface AppConfigRepository {
    fun enquireFreshInstall(): Flow<Boolean>
    suspend fun setIsInstalled()
}

class AppConfigRepositoryImp(preferencesDataStore: DataStore<Preferences>) :
    AppConfigRepository {

    private val appConfig by lazy {
        AppConfig(preferencesDataStore)
    }

    override fun enquireFreshInstall() = appConfig.enquireFreshInstall()

    override suspend fun setIsInstalled() {
        withContext(Dispatchers.IO) {
            appConfig.setInstalled()
        }
    }
}