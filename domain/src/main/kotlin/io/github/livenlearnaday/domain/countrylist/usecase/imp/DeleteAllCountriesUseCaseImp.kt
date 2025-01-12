package io.github.livenlearnaday.domain.survey.usecases.imp

import io.github.livenlearnaday.domain.countrylist.usecase.DeleteAllCountriesUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository

class DeleteAllCountriesUseCaseImp(
    private val countryListRepository: CountryListRepository
) : DeleteAllCountriesUseCase {

    override suspend fun execute() {
        countryListRepository.clearCountryTable()
    }
}