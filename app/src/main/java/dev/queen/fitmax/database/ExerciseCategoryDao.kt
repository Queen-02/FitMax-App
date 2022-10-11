package dev.queen.fitmax.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.queen.fitmax.models.ExerciseCategory

@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExerciseCategory(exerciseCategory: ExerciseCategory)

    @Query("SELECT * FROM exercisecategory")
    fun getExerciseCategory():LiveData<List<ExerciseCategory>> //changing the name in order to query the Api call
}