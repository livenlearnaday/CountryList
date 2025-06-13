package io.github.livenlearnaday.domain.countrylist.usecase.imp

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.SaveCountriesUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository

class SaveCountriesUseCaseImp(
    private val countryListRepository: CountryListRepository
) : SaveCountriesUseCase {
    override suspend fun execute(countries: List<CountryModel>) {
        countryListRepository.insertCountries(countries)
    }
}
