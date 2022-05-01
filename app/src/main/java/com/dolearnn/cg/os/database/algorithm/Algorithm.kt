package com.dolearnn.cg.os.database.algorithm

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.CONTROL_TYPE
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.DESCRIPTION
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.ICON_URL
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.ID
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.NAME
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.ORDER
import com.dolearnn.cg.os.database.schema.Columns.Algorithms.VIEW_ID
import com.dolearnn.cg.os.database.schema.Tables

@Entity(tableName = Tables.ALGORITHM)
data class Algorithm(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = ID)
    val id: Int = 0,

    @NonNull
    @ColumnInfo(name = NAME)
    val name: String,

    @NonNull
    @ColumnInfo(name = DESCRIPTION)
    val description: String,

    @NonNull
    @ColumnInfo(name = ORDER)
    val order: Int,

    @NonNull
    @ColumnInfo(name = ICON_URL)
    val iconURL: String,

    @NonNull
    @ColumnInfo(name = VIEW_ID)
    val viewId: Int = -1,

    @NonNull
    @ColumnInfo(name = CONTROL_TYPE)
    val controlType: Int = ControlType.PLAYER_CONTROLS.ordinal,
)

enum class ControlType {
    ALL_CONTROLS, PLAYER_CONTROLS
}