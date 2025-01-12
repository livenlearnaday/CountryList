package io.github.livenlearnaday.countrylistkotlin.data.converter

import androidx.room.TypeConverter
import io.github.livenlearnaday.countrylistkotlin.data.entity.LanguageEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Collections

class LanguageListConverter {

    @TypeConverter
    fun stringToLanguageObjectList(data: String?): MutableList<LanguageEntity> {
        if (data == null) {
            return Collections.emptyList()
        }
        return Json.decodeFromString(data)
    }

    @TypeConverter
    fun languageObjectListToString(someObjects: List<LanguageEntity>): String {
        return Json.encodeToString(someObjects)
    }

}