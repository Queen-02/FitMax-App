package dev.queen.fitmax.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dev.queen.fitmax.models.WorkOutPlanItem
import dev.queen.fitmax.models.WorkoutPlan
import dev.queen.fitmax.repository.WorkoutPlanRepo
import kotlinx.coroutines.launch
import java.util.UUID

class WorkoutPlanViewModel : ViewModel() {
    val workoutPlanRepo = WorkoutPlanRepo()
    lateinit var workoutPlanLiveData : LiveData<WorkoutPlan>
    var selectedExerciseIds = mutableListOf<String>()

    fun saveWorkoutPlan(workoutPlan: WorkoutPlan) {
        viewModelScope.launch {
            workoutPlanRepo.saveWorkoutPlan(workoutPlan)
        }
    }

    fun getExistingWorkoutPlan(userId : String){
        workoutPlanLiveData = workoutPlanRepo.getWorkoutPlanByUserId(userId)
    }

    fun createWorkoutPlanItem(dayNumber: Int, workoutPlanId: String){
        val workoutPlanItem = WorkOutPlanItem(
            workoutPlanItemId = UUID.randomUUID().toString(),
            workoutPlanId= workoutPlanId,
            day = dayNumber,
            exerciseId = selectedExerciseIds
        )

        viewModelScope.launch {
            workoutPlanRepo.saveWorkoutPlanItem(workoutPlanItem)
        }
    }

    fun getTodayWorkoutPlanItem(workoutPlanId : String, dayNumber : Int): LiveData<WorkOutPlanItem> {
        return workoutPlanRepo.getTodayWorkoutPlanItem(workoutPlanId, dayNumber)
    }

}