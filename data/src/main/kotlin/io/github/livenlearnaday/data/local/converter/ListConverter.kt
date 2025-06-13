package io.github.livenlearnaday.countrylistkotlin.data.converter

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> = flatStringList.split(",")

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String = listOfString.joinToString(",")
}
