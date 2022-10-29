package dev.queen.fitmax.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.queen.fitmax.models.WorkoutPlan
import dev.queen.fitmax.repository.WorkoutPlanRepo
import kotlinx.coroutines.launch

class WorkoutPlanViewModel : ViewModel() {
    val workoutPlanRepo = WorkoutPlanRepo()
    lateinit var workoutPlanLiveData : LiveData<WorkoutPlan>

    fun saveWorkoutPlan(workoutPlan: WorkoutPlan) {
        viewModelScope.launch {
            workoutPlanRepo.saveWorkoutPlan(workoutPlan)
        }
    }

    fun getExistingWorkoutPlan(userId : String){
        workoutPlanLiveData = workoutPlanRepo.getWorkoutPlanByUserId(userId)
    }
}