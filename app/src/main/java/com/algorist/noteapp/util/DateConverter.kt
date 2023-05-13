package com.algorist.noteapp.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromTimeStamp(timestamp: Long) = Date(timestamp)

    @TypeConverter
    fun toTimeStamp(date: Date) = date.time
}