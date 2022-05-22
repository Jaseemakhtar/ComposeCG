package com.dolearnn.cg.data.database.algorithm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dolearnn.cg.data.database.schema.Columns.Algorithms.ID
import com.dolearnn.cg.data.database.schema.Columns.Algorithms.ORDER
import com.dolearnn.cg.data.database.schema.Tables.ALGORITHM

@Dao
interface AlgorithmDao {
    @Query("SELECT * FROM $ALGORITHM ORDER BY `$ORDER`")
    fun getAlgorithms(): LiveData<List<Algorithm>>

    @Query("SELECT * FROM $ALGORITHM WHERE $ID = :id")
    fun getAlgorithm(id: Int): Algorithm

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(algorithms: List<Algorithm>)
}