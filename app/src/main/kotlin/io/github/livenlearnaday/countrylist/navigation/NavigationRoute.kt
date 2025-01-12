package io.github.livenlearnaday.countrylist.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class NavigationRoute {
    @Serializable
    object CountryList

    @Serializable
    data class CountryDetail(
        val countryNameArgValue: String
    )

    @Serializable
    object Settings


}

