package io.github.livenlearnaday.presentation.countrylist

import androidx.annotation.StringRes
import io.github.livenlearnaday.presentation.R

sealed class CustomMenuItem(
    val title: String = "",
    @StringRes val warningTextRes: Int?
) {

    data object ShowAllFavs : CustomMenuItem(
        title = "Show All Favs",
        warningTextRes = null
    )

    data object FetchFromNetwork : CustomMenuItem(
        title = "Fetch Data",
        warningTextRes = R.string.dialog_reload_countries_message
    )

    data object ClearAllFavs : CustomMenuItem(
        title = "Clear All Favs",
        warningTextRes = R.string.dialog_reset_fav_countries_message
    )

    data object None : CustomMenuItem(
        title = "",
        warningTextRes = null
    )

    companion object {
        val customMenuItems = listOf(ShowAllFavs, FetchFromNetwork, ClearAllFavs)
        val longestText = customMenuItems.map { it.title }.maxBy { it.length }
    }
}
