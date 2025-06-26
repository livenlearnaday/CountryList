package io.github.livenlearnaday.presentation.countrylist

import io.github.livenlearnaday.domain.countrylist.model.CountryModel

data class CountryListState(
    val countryItems: List<CountryModel> = emptyList(),
    val fetchDataUnixTimeStampInSeconds: String = "",
    val isLoading: Boolean = false,
    val showShowDialog: Boolean = false,
    val showMenu: Boolean = false,
    val showMenuWarning: Boolean = false,
    val customMenuItem: CustomMenuItem = CustomMenuItem.None,
    val showSearchBar: Boolean = false,
    val showAllFav: Boolean = false,
    val searchResults: List<CountryModel> = emptyList()
)
