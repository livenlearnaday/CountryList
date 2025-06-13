package io.github.livenlearnaday.data.countrylist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageDto(
    var name: String?
) {
    @SerialName("nativeName")
    var nativeName: String? = null

    @SerialName("iso639_1")
    var iso6391: String? = null

    @SerialName("iso639_2")
    var iso6392: String? = null
}
