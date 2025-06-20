package io.github.livenlearnaday.countrylistkotlin.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class LanguageEntity(
    var name: String = "",
    var nativeName: String = "",
    var iso6391: String = "",
    var iso6392: String = ""
)
