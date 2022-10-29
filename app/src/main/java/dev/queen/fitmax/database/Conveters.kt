package dev.queen.fitmax.database

import androidx.room.TypeConverter

class Conveters {
    @TypeConverter
    fun listToString(listX: List<String>): String {
        return listX.joinToString(",")
        //        var outputString = ""
//        listX.forEach { item -> outputString += "$item," }
//        return outputString
    }

    @TypeConverter
    fun stringToList(stringX: String) : List<String>{
        return stringX.split(",")
    }
}