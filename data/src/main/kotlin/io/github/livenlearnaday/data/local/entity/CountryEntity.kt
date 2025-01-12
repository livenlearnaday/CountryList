package io.github.livenlearnaday.countrylistkotlin.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "country_table")
@OptIn(ExperimentalSerializationApi::class)
data class CountryEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String = "",
    var capital: String = "",
    var region: String = "",
    var subregion: String = "",
    var flag: String = "",
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    var languageEntities: List<LanguageEntity> = emptyList(),
    var callingCodes: List<String> = emptyList(),
    var isFav: Boolean = false,
    var note: String = ""

)











