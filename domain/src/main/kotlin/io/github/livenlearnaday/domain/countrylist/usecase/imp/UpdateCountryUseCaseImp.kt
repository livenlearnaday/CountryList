package io.github.livenlearnaday.domain.countrylist.usecase.imp

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository

class UpdateCountryUseCaseImp(
    private val countryListRepository: CountryListRepository
) : UpdateCountryUseCase {
    override suspend fun execute(country: CountryModel) {
        countryListRepository.updateCountry(country)
    }
}
