package dev.queen.fitmax.repository

import androidx.lifecycle.LiveData
import dev.queen.fitmax.FitMax
import dev.queen.fitmax.api.APIClient
import dev.queen.fitmax.api.APIInterface
import dev.queen.fitmax.database.FitmaxDB
import dev.queen.fitmax.models.Exercise
import dev.queen.fitmax.models.ExerciseCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient = APIClient.buildAPIClient(APIInterface::class.java)
    val database = FitmaxDB.getDatabase(FitMax.appContext)
    val exerciseCategoryDao = database.exerciseCategoryDao()
    val exerciseDao = database.exerciseDao()


    suspend fun fetchApiExerciseCategories(accessToken: String) =
        withContext(Dispatchers.IO) {
            val response = apiClient.fetchExerciseCategories(accessToken)
            if (response.isSuccessful) {
                val exerciseCategories = response.body()
                if (exerciseCategories != null) {
                    exerciseCategories.forEach { category ->
                        exerciseCategoryDao.insertExerciseCategory(category)
                    }
                }
            }
            return@withContext response
        }
    fun getDbExerciseCategories(): LiveData<List<ExerciseCategory>>{
        return exerciseCategoryDao.getExerciseCategory()
    }

    suspend fun fetchApiExercises(accessToken: String){
        withContext(Dispatchers.IO){
            val response = apiClient.fetchExercises(accessToken)
            if (response.isSuccessful){
                val exercises = response.body()
                exercises?.forEach { exercise ->
                    exerciseDao.insertExercises(exercise)
                }
            }
            return@withContext response
        }
    }

    fun getDbExercises() : LiveData<List<Exercise>>{
        return exerciseDao.getExercises()
    }

    fun getExerciseByCategoryId(categoryId : String): LiveData<List<Exercise>>{
        return exerciseDao.fetchExercisesByCategory(categoryId)

    }
}