package com.dolearnn.cg.data.api

import android.content.Context
import com.dolearnn.cg.data.ApiResponse
import com.dolearnn.cg.data.database.algorithm.Algorithm
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AlgorithmsApi {

    @Throws(Exception::class)
    private fun fetchAlgorithms(context: Context): List<Algorithm> {
        // For now fetching data from local json
        // Later will use remote API
        val inputStream = context.assets.open("api/algorithms.json")
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()

        val jsonString = String(buffer, Charsets.UTF_8)

        return Gson().fromJson(
            jsonString,
            object : TypeToken<List<Algorithm>>() {}.type
        )
    }

    operator fun invoke(context: Context): ApiResponse<List<Algorithm>> = try {
        val algorithms = fetchAlgorithms(context = context)
        ApiResponse.Success(algorithms)
    } catch (e: Exception) {
        ApiResponse.Error(e)
    }
}