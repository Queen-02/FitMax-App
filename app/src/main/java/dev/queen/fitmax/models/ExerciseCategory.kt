package dev.queen.fitmax.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ExerciseCategory")
data class ExerciseCategory(
    @PrimaryKey @SerializedName("category_name") val categoryName : String,
    @SerializedName("category_id") val categoryId : String
)
