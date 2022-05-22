package com.dolearnn.cg

import android.app.Application
import com.dolearnn.cg.data.database.AppDatabase

class DoLearnnCgApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}