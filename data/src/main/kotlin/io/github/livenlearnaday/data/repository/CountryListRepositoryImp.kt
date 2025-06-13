package io.github.livenlearnaday.data.repository

import io.github.livenlearnaday.data.local.CountryListLocalDataSource
import io.github.livenlearnaday.data.mappers.toCountryEntities
import io.github.livenlearnaday.data.mappers.toCountryEntity
import io.github.livenlearnaday.data.mappers.toCountryModel
import io.github.livenlearnaday.data.mappers.toErrorResponseModel
import io.github.livenlearnaday.data.remote.CountryListRemoteDataSource
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.DataError
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.model.ErrorModel
import io.github.livenlearnaday.domain.model.ErrorResponseModel
import io.github.livenlearnaday.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountryListRepositoryImp(
    private val countryListRemoteDataSource: CountryListRemoteDataSource,
    private val countryListLocalDataSource: CountryListLocalDataSource
) : CountryListRepository {
    override suspend fun fetchCountriesFromApi(): CheckResult<List<CountryModel>, DataError.Network, ErrorResponseModel> = when (val apiResponse = countryListRemoteDataSource.fetchCountries()) {
        is CheckResult.Success -> {
            CheckResult.Success(apiResponse.data.map { it.toCountryModel() })
        }

        is CheckResult.Failure -> {
            CheckResult.Failure(
                exceptionError = apiResponse.exceptionError,
                responseError = apiResponse.responseError?.toErrorResponseModel()
                    ?: ErrorResponseModel(listOf(ErrorModel(code = "1", message = "unknown error")))
            )
        }
    }

    override fun fetchCountriesFromDb(): Flow<List<CountryModel>> {
        val result = countryListLocalDataSource.fetchCountries()
            .map { countries ->
                countries.map {
                    it.toCountryModel()
                }
            }
        return result
    }

    override fun fetchSearchCountries(
        query: String
    ): Flow<List<CountryModel>> {
        val result = countryListLocalDataSource.fetchSearchCountries(query)
            .map { countries ->
                countries.map {
                    it.toCountryModel()
                }
            }
        return result
    }

    override suspend fun insertCountries(countries: List<CountryModel>) {
        countryListLocalDataSource.insertCountries(countries.toCountryEntities())
    }

    override suspend fun updateCountry(country: CountryModel) {
        countryListLocalDataSource.updateCountry(country.toCountryEntity())
    }

    override suspend fun updateCountryFav(fav: Boolean, countryName: String) {
        countryListLocalDataSource.updateCountryFav(fav, countryName)
    }

    override suspend fun clearAllFavCountries() {
        countryListLocalDataSource.clearAllFavCountries()
    }

    override suspend fun clearCountryTable() {
        countryListLocalDataSource.clearCountryTable()
    }

    override fun fetchCountryFromDbByName(name: String): Flow<CountryModel> {
        val result = countryListLocalDataSource.fetchCountryFromDbByName(name)
            .map { it.toCountryModel() }
        return result
    }
}
