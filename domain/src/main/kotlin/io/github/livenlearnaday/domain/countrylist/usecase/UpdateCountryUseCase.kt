package io.github.livenlearnaday.domain.countrylist.usecase

import io.github.livenlearnaday.domain.countrylist.model.CountryModel

fun interface UpdateCountryUseCase {
    suspend fun execute(country: CountryModel)
}