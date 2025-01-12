package io.github.livenlearnaday.domain.survey.usecases.imp

import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.DataError
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromApiUseCase
import io.github.livenlearnaday.domain.model.ErrorResponseModel
import io.github.livenlearnaday.domain.repository.CountryListRepository

class FetchCountriesFromApiUseCaseImp(
    private val countryListRepository: CountryListRepository
) : FetchCountriesFromApiUseCase {
    override suspend fun execute(): CheckResult<List<CountryModel>, DataError.Network, ErrorResponseModel> {
        return countryListRepository.fetchCountriesFromApi()
    }
}