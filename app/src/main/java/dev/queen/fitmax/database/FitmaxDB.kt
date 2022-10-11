package dev.queen.fitmax.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.queen.fitmax.models.Exercise
import dev.queen.fitmax.models.ExerciseCategory

@Database(entities = arrayOf(ExerciseCategory::class, Exercise::class), version = 3)
abstract class FitmaxDB: RoomDatabase() {
    abstract fun exerciseCategoryDao(): ExerciseCategoryDao
    abstract fun exerciseDao(): ExerciseDao

    companion object{
        private var database: FitmaxDB? = null
        fun getDatabase(context: Context): FitmaxDB{
            if (database==null){
                database= Room.databaseBuilder(context, FitmaxDB::class.java, "FitmaxDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as FitmaxDB
        }
    }
}