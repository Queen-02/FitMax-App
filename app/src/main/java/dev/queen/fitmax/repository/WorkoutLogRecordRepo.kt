package dev.queen.fitmax.repository

import androidx.lifecycle.LiveData
import dev.queen.fitmax.FitMax
import dev.queen.fitmax.database.FitmaxDB
import dev.queen.fitmax.models.WorkoutLogRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutLogRecordRepo{
    var database = FitmaxDB.getDatabase(FitMax.appContext)
    val wrkoutLogRecordDao = database.wrklogRecordDao()

    suspend fun saveWrkLogRecord(workoutLogRecord: WorkoutLogRecord){
        withContext(Dispatchers.IO){
            wrkoutLogRecordDao.insertWorkoutLogRecord(workoutLogRecord)
        }
    }
//
    fun getTodayWorkoutLogRecord(userId : String, currentDate : String) : LiveData<List<WorkoutLogRecord>>{
        return wrkoutLogRecordDao.getWorkoutLogByUserId(userId, currentDate)
    }
}