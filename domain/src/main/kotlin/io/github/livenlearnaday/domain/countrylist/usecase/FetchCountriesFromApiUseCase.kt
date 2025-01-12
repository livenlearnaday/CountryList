package io.github.livenlearnaday.domain.countrylist.usecase

import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.DataError
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.model.ErrorResponseModel

fun interface FetchCountriesFromApiUseCase {
    suspend fun execute(): CheckResult<List<CountryModel>, DataError.Network, ErrorResponseModel>
}