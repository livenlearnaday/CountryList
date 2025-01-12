package io.github.livenlearnaday.domain.countrylist.usecase.imp

import io.github.livenlearnaday.domain.countrylist.usecase.ClearAllCountriesFavUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository

class ClearAllCountriesFavUseCaseImp(
private val countryListRepository: CountryListRepository
): ClearAllCountriesFavUseCase {
    override suspend fun execute() {
        countryListRepository.clearAllFavCountries()
    }
}