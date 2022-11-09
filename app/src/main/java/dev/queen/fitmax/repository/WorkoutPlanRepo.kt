package dev.queen.fitmax.repository

import androidx.lifecycle.LiveData
import dev.queen.fitmax.FitMax
import dev.queen.fitmax.database.FitmaxDB
import dev.queen.fitmax.models.WorkOutPlanItem
import dev.queen.fitmax.models.WorkoutPlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutPlanRepo {
    val database = FitmaxDB.getDatabase(FitMax.appContext)
    val workoutPlanDao = database.workoutPlan()
    val workoutPlanItemDao = database.workoutPlanItemDao()

    suspend fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        withContext(Dispatchers.IO){
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }

    suspend fun saveWorkoutPlanItem(workoutPlanItem: WorkOutPlanItem){
        withContext(Dispatchers.IO){
            workoutPlanItemDao.insertWorkoutPlanItem(workoutPlanItem)
        }
    }

    fun getWorkoutPlanByUserId(userId: String): LiveData<WorkoutPlan>{
        return  workoutPlanDao.getWorkoutPlanByUserId(userId)
    }

    fun getTodayWorkoutPlanItem(workoutPlanId : String, dayNumber : Int): LiveData<WorkOutPlanItem>{
        return workoutPlanItemDao.getTodayWorkoutPlanItem(workoutPlanId, dayNumber)
    }
}