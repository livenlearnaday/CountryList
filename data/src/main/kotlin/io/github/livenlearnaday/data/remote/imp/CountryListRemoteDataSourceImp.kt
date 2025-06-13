package io.github.livenlearnaday.data.remote.imp

import io.github.livenlearnaday.data.countrylist.CountryDto
import io.github.livenlearnaday.data.models.ErrorResponseDto
import io.github.livenlearnaday.data.remote.ApiRoutes
import io.github.livenlearnaday.data.remote.CountryListRemoteDataSource
import io.github.livenlearnaday.data.util.safeApiRequest
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.DataError
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CountryListRemoteDataSourceImp(
    private val httpClient: HttpClient
) : CountryListRemoteDataSource {
    override suspend fun fetchCountries(): CheckResult<List<CountryDto>, DataError.Network, ErrorResponseDto> {
        val safeResult = safeApiRequest<List<CountryDto>> {
            val response = httpClient
                .get(urlString = "${ApiRoutes.COUNTRY_LIST_BASE}${ApiRoutes.COUNTRY_LIST_JSON}")

            response
        }

        return safeResult
    }
}
