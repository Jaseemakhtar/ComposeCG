package com.dolearnn.cg.os

import android.app.Application
import com.dolearnn.cg.os.database.AppDatabase

class DoLearnnCgApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}