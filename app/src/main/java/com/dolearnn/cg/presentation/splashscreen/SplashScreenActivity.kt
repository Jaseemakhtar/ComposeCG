package com.dolearnn.cg.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dolearnn.cg.presentation.HomeActivity
import com.dolearnn.cg.workers.DbSyncWorker

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