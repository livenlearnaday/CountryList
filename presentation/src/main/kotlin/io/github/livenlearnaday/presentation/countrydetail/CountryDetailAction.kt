package io.github.livenlearnaday.presentation.countrydetail

import io.github.livenlearnaday.domain.countrylist.model.CountryModel

sealed interface CountryDetailAction {
    data class OnCountryFavIconClicked(val country: CountryModel) : CountryDetailAction
    data object FetchData : CountryDetailAction
}
