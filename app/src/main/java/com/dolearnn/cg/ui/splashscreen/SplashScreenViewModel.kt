package com.dolearnn.cg.ui.splashscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dolearnn.cg.data.datastore.appConfigDataStore
import com.dolearnn.cg.data.repository.AppConfigRepository
import com.dolearnn.cg.data.repository.AppConfigRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val appConfigRepository: AppConfigRepository =
        AppConfigRepositoryImp(application.appConfigDataStore)

    val enquireFreshInstall: Flow<Boolean> = appConfigRepository.enquireFreshInstall()

    fun setInstalled() {
        viewModelScope.launch {
            appConfigRepository.setIsInstalled()
        }
    }
}