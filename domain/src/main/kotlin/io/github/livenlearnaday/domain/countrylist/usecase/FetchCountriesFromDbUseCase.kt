package io.github.livenlearnaday.domain.countrylist.usecase

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import kotlinx.coroutines.flow.Flow

fun interface FetchCountriesFromDbUseCase {
    fun execute(): Flow<List<CountryModel>>
}