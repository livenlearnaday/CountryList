package io.github.livenlearnaday.countrylistkotlin.data.converter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
