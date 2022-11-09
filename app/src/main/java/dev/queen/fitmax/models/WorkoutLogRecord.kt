package dev.queen.fitmax.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutLogRecord(
    @PrimaryKey var wrkoutLogId: String,
    var date: String,
    var exerciseId: String,
    var set: Int,
    var weight: Int?,
    var reps: Int,
    var wrkPlanItemId: String,
    var userId: String

    )
