package com.dolearnn.cg.os.database.algorithm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.ID
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.ORDER
import com.dolearnn.cg.os.database.schema.Tables.ALGORITHM

@Dao
interface AlgorithmDao {
    @Query("SELECT * FROM $ALGORITHM ORDER BY `$ORDER`")
    fun getAlgorithms(): LiveData<List<Algorithm>>

    @Query("SELECT * FROM $ALGORITHM WHERE $ID = :id")
    fun getAlgorithm(id: Int): Algorithm
}