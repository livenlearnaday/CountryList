package io.github.livenlearnaday.domain.countrylist.usecase

import io.github.livenlearnaday.domain.countrylist.model.CountryModel

fun interface SaveCountriesUseCase {
    suspend fun execute(countries: List<CountryModel>)
}
