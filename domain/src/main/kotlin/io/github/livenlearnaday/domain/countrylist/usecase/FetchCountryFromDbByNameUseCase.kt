package io.github.livenlearnaday.domain.countrylist.usecase

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface FetchCountryFromDbByNameUseCase {
    fun execute(name: String): Flow<CountryModel>
}
