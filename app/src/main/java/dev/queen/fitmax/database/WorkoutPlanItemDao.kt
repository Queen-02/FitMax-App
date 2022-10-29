package dev.queen.fitmax.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.queen.fitmax.models.WorkOutPlanItem

@Dao
interface WorkoutPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlanItem(workOutPlanItem: WorkOutPlanItem)
}