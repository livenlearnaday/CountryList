package io.github.livenlearnaday.presentation.countrydetail

import io.github.livenlearnaday.domain.countrylist.model.CountryModel

data class CountryDetailState(
    val countryName: String = "",
    val country: CountryModel = CountryModel(),
    val isLoading: Boolean = false
)
