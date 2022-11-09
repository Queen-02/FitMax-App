package dev.queen.fitmax.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.queen.fitmax.models.WorkoutLogRecord
import dev.queen.fitmax.repository.WorkoutLogRecordRepo
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WorkoutLogRecordViewModel:ViewModel() {
    val workoutLogRepository=WorkoutLogRecordRepo()
    lateinit var todaysRecordLiveData:LiveData<List<WorkoutLogRecord>>

    fun saveWorkoutLogRecord(workoutLogRecord:WorkoutLogRecord){
        viewModelScope.launch {
            workoutLogRepository.saveWrkLogRecord(workoutLogRecord)
        }
    }
    //    fun getExistingWorkoutLogRecord(){
//        todaysRecordLiveData
//    }
    fun getTodayWorkoutRecords(userId:String){
        todaysRecordLiveData=workoutLogRepository.getTodayWorkoutLogRecord(userId, getCurrentDate())
    }

    fun getCurrentDate():String{
        val formartter=SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return formartter.format(Date())
//        return formartter.parse(dateStr)!!
    }

}