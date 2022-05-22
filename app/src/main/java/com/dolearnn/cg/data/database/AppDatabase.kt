package com.dolearnn.cg.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dolearnn.cg.data.database.algorithm.Algorithm
import com.dolearnn.cg.data.database.algorithm.AlgorithmDao
import com.dolearnn.cg.data.database.schema.SchemaVersions

@Database(
    entities = [Algorithm::class],
    version = SchemaVersions.CURRENT,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun algorithmDao(): AlgorithmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val APP_DATABASE = "app_database"

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    APP_DATABASE
                ).createFromAsset("database/dolearnn_cg.db").build()

                INSTANCE = instance

                instance
            }
        }
    }
}