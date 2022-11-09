package dev.queen.fitmax.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.queen.fitmax.models.*

@Database(
    entities = arrayOf(
        ExerciseCategory::class,
        Exercise::class,
        WorkoutPlan::class,
        WorkOutPlanItem::class,
        WorkoutLogRecord::class,
    ), version = 6
)
@TypeConverters(Conveters::class)
abstract class FitmaxDB : RoomDatabase() {
    abstract fun exerciseCategoryDao(): ExerciseCategoryDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutPlan(): WorkoutPlanDao
    abstract fun workoutPlanItemDao(): WorkoutPlanItemDao
    abstract fun wrklogRecordDao() : WorkoutLogRecordDao

    companion object {
        private var database: FitmaxDB? = null
        fun getDatabase(context: Context): FitmaxDB {
            if (database == null) {
                database = Room.databaseBuilder(context, FitmaxDB::class.java, "FitmaxDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as FitmaxDB
        }
    }
}