package dev.queen.fitmax.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.queen.fitmax.models.Exercise
import dev.queen.fitmax.models.ExerciseCategory
import dev.queen.fitmax.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {
    val exerciseRepository = ExerciseRepository()

    lateinit var exerciseCategoryLiveData : LiveData<List<ExerciseCategory>>
    val errorLiveData = MutableLiveData<String>()
    lateinit var exerciseLiveData: LiveData<List<Exercise>>

    var selectedExerciseIds = mutableListOf<String>()

    fun fetchAPiExerciseCatrgories(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchApiExerciseCategories(accessToken)
            if (!response.isSuccessful) {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }

    }
    fun getDbExerciseCategories(){
        exerciseCategoryLiveData = exerciseRepository.getDbExerciseCategories()
    }

    fun fetchApiExercises(accessToken: String) {
        viewModelScope.launch {

            exerciseRepository.fetchApiExercises(accessToken)
        }
    }

    fun getDbExercises(){
        exerciseLiveData = exerciseRepository.getDbExercises()
    }

    fun getExerciseByCategoryId(categoryId: String){
        exerciseLiveData = exerciseRepository.getExerciseByCategoryId(categoryId)
    }
}