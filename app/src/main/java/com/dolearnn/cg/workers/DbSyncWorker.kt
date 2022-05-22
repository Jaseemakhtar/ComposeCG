package com.dolearnn.cg.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.dolearnn.cg.DoLearnnCgApplication
import com.dolearnn.cg.data.ApiResponse
import com.dolearnn.cg.data.repository.AlgorithmsRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbSyncWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val algorithmsDao by lazy {
        (applicationContext as DoLearnnCgApplication).database.algorithmDao()
    }

    private val algorithmsRepository: AlgorithmsRepositoryImp by lazy {
        AlgorithmsRepositoryImp()
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        when (val response = algorithmsRepository.getAlgorithms(applicationContext)) {
            is ApiResponse.Error -> Result.retry()
            is ApiResponse.Success -> {
                algorithmsDao.insertAll(response.data)
                Result.success()
            }
        }
    }

    companion object {
        const val DB_SYNC_WORKER = "sync_worker"

        fun schedule(context: Context) {
            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    DB_SYNC_WORKER,
                    ExistingWorkPolicy.REPLACE,
                    OneTimeWorkRequestBuilder<DbSyncWorker>()
                        .setConstraints(
                            Constraints.Builder().build()
                        )
                        .build()
                )
        }
    }
}