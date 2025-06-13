package io.github.livenlearnaday.data.local

import io.github.livenlearnaday.countrylistkotlin.data.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

interface CountryListLocalDataSource {
    fun fetchCountries(): Flow<List<CountryEntity>>

    fun fetchSearchCountries(query: String): Flow<List<CountryEntity>>

    suspend fun insertCountries(countries: List<CountryEntity>)

    suspend fun updateCountry(country: CountryEntity)

    suspend fun updateCountryFav(fav: Boolean, countryName: String)

    suspend fun clearAllFavCountries()

    suspend fun clearCountryTable()

    fun fetchCountryFromDbByName(name: String): Flow<CountryEntity>
}
