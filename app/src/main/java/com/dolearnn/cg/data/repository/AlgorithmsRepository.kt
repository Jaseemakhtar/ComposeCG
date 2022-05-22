package com.dolearnn.cg.data.repository

import android.content.Context
import com.dolearnn.cg.data.ApiResponse
import com.dolearnn.cg.data.api.AlgorithmsApi
import com.dolearnn.cg.data.database.algorithm.Algorithm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AlgorithmsRepository {
    suspend fun getAlgorithms(context: Context): ApiResponse<List<Algorithm>>
}

class AlgorithmsRepositoryImp : AlgorithmsRepository {
    private val algorithmsApi by lazy {
        AlgorithmsApi()
    }

    override suspend fun getAlgorithms(context: Context): ApiResponse<List<Algorithm>> =
        withContext(Dispatchers.IO) {
            algorithmsApi(context)
        }
}

