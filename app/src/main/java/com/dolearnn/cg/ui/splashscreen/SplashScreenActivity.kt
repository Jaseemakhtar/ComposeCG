package com.dolearnn.cg.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dolearnn.cg.ui.home.HomeActivity
import com.dolearnn.cg.workers.DbSyncWorker
import kotlinx.coroutines.flow.collect

class SplashScreenActivity : ComponentActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun observe() {
        lifecycleScope.launchWhenCreated {
            viewModel.enquireFreshInstall.collect { isFreshInstall ->
                if (isFreshInstall) {
                    DbSyncWorker.schedule(this@SplashScreenActivity)
                    viewModel.setInstalled()
                }
            }
        }
    }
}