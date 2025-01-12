package io.github.livenlearnaday.countrylistkotlin.data.converter

import androidx.room.TypeConverter


class ListConverter {

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
    }


}


