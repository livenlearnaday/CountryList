package io.github.livenlearnaday.data.remote

import io.github.livenlearnaday.data.countrylist.CountryDto
import io.github.livenlearnaday.data.models.ErrorResponseDto
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.DataError

interface CountryListRemoteDataSource {
    suspend fun fetchCountries(): CheckResult<List<CountryDto>, DataError.Network, ErrorResponseDto>
}
