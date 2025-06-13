package io.github.livenlearnaday.data.countrylist

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val name: String,
    var capital: String?,
    var region: String?,
    var subregion: String?,
    var flag: String?,
    var languages: List<LanguageDto>?,
    var callingCodes: List<String>?,
    var isFav: Boolean? = null,
    var note: String? = null
)
