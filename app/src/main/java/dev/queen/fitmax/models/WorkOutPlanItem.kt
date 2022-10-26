package dev.queen.fitmax.models

data class WorkOutPlanItem(
    var workoutPlanItemId: String,
    var workoutPlan: String,
    var day: Int,
    var exerciseId: List<String>,
)
