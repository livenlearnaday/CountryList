package io.github.livenlearnaday.presentation.countrylist

import io.github.livenlearnaday.domain.countrylist.model.CountryModel

sealed interface CountryListAction {
    data class OnCountryItemFavClicked(val countryItem: CountryModel) : CountryListAction
    data object OnFetchCountryListFromApi : CountryListAction
    data object OnMoreIconClicked : CountryListAction
    data class OnSelectMenuItem(val menuItem: CustomMenuItem) : CountryListAction
    data object OnDismissMenu : CountryListAction
    data object OnDismissDialog : CountryListAction
    data object OnSearchIconClicked : CountryListAction
    data class OnSearchQuerySubmit(val query: String) : CountryListAction
    data object OnClearAllFavs : CountryListAction
    data object OnShowFavs : CountryListAction
    data object OnRefreshListScreen : CountryListAction
    data object OnExitSearchMode : CountryListAction
}
