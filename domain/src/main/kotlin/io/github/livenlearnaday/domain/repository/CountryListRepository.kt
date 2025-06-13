package io.github.livenlearnaday.domain.repository

import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.DataError
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.model.ErrorResponseModel
import kotlinx.coroutines.flow.Flow

interface CountryListRepository {

    suspend fun fetchCountriesFromApi(): CheckResult<List<CountryModel>, DataError.Network, ErrorResponseModel>

    fun fetchCountriesFromDb(): Flow<List<CountryModel>>

    fun fetchSearchCountries(query: String): Flow<List<CountryModel>>

    suspend fun insertCountries(countries: List<CountryModel>)

    suspend fun updateCountry(country: CountryModel)

    suspend fun updateCountryFav(fav: Boolean, countryName: String)

    suspend fun clearAllFavCountries()

    suspend fun clearCountryTable()

    fun fetchCountryFromDbByName(name: String): Flow<CountryModel>
}
