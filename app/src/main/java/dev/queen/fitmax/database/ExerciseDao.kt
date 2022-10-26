package dev.queen.fitmax.database

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.queen.fitmax.models.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercise: Exercise)

    @Query("SELECT * FROM Exercises")
    fun getExercises() : LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE categoryId = :categoryId")
    fun fetchExercisesByCategory(categoryId: String): LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE categoryId = :selectedCategoryId")
    fun getExercisesByCategoryId(selectedCategoryId: String) : LiveData<List<Exercise>>

}