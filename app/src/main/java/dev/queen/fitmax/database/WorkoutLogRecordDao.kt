package dev.queen.fitmax.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.lifecycle.LiveData
import androidx.room.Query
import dev.queen.fitmax.models.WorkoutLogRecord
import dev.queen.fitmax.repository.WorkoutLogRecordRepo

@Dao
interface WorkoutLogRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord)

    @Query("SELECT * FROM workoutlogrecord WHERE userId=:userId AND date >= :currentDate")
    fun getWorkoutLogByUserId(userId:String, currentDate:String): LiveData<List<WorkoutLogRecord>>

}