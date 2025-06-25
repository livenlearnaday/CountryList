package io.github.livenlearnaday.presentation.mockdata

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.model.LanguageModel

object CountryMockData {

    val countryThailand = CountryModel(
        name = "Thailand",
        capital = "Bangkok",
        languages = listOf(LanguageModel(name = "Thai")),
        region = "Southeast Asia",
        subregion = "Indochinese Peninsula",
        callingCodes = listOf("66")
    )
}
