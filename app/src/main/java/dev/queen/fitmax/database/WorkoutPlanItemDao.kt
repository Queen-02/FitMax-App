package dev.queen.fitmax.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.queen.fitmax.models.WorkOutPlanItem
import java.sql.RowId

@Dao
interface WorkoutPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlanItem(workOutPlanItem: WorkOutPlanItem)

    @Query("SELECT * FROM workoutplanitem WHERE workoutPlanId = :workoutPlanId AND day = :dayNumber")
    fun getTodayWorkoutPlanItem(workoutPlanId: String, dayNumber: Int): LiveData<WorkOutPlanItem>
}