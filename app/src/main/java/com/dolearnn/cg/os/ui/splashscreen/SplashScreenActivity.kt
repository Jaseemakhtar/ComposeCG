package com.dolearnn.cg.os.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dolearnn.cg.os.ui.home.HomeActivity

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}