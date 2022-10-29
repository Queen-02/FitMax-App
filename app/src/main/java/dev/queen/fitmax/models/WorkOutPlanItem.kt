package dev.queen.fitmax.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WorkoutPlanItem")
data class WorkOutPlanItem(
    @PrimaryKey var workoutPlanItemId: String,
    var workoutPlan: String,
    var day: Int,
    var exerciseId: List<String>,
)
