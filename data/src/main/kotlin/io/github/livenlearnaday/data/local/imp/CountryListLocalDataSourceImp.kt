package io.github.livenlearnaday.data.local.imp

import io.github.livenlearnaday.countrylistkotlin.data.entity.CountryEntity
import io.github.livenlearnaday.data.local.CountryListLocalDataSource
import io.github.livenlearnaday.data.local_db.CountryDao
import kotlinx.coroutines.flow.Flow

class CountryListLocalDataSourceImp(
    private val countryDao: CountryDao
) : CountryListLocalDataSource {
    override fun fetchCountries(): Flow<List<CountryEntity>> = countryDao.fetchCountries()

    override fun fetchSearchCountries(query: String): Flow<List<CountryEntity>> = countryDao.fetchCountriesSearchResults(query)

    override suspend fun insertCountries(countries: List<CountryEntity>) {
        countryDao.insertCountries(countries)
    }

    override suspend fun updateCountry(country: CountryEntity) {
        countryDao.updateCountry(country)
    }

    override suspend fun updateCountryFav(fav: Boolean, countryName: String) {
        countryDao.updateCountryFav(fav, countryName)
    }

    override suspend fun clearAllFavCountries() {
        countryDao.clearAllFavCountries()
    }

    override suspend fun clearCountryTable() {
        countryDao.clearCountryTable()
    }

    override fun fetchCountryFromDbByName(name: String): Flow<CountryEntity> = countryDao.fetchCountryFromDbByName(name)
}
