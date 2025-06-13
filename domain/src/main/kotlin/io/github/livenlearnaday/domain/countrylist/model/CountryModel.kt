package io.github.livenlearnaday.domain.countrylist.model

data class CountryModel(
    val id: Int = 0,
    val name: String = "",
    var capital: String = "",
    var region: String = "",
    var subregion: String = "",
    var flag: String = "",
    var languages: List<LanguageModel> = emptyList(),
    var callingCodes: List<String> = emptyList(),
    var isFav: Boolean = false,
    var note: String = ""
)
