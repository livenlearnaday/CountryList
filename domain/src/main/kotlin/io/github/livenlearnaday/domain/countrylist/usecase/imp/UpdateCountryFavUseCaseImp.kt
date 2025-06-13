package io.github.livenlearnaday.domain.countrylist.usecase.imp

import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository

class UpdateCountryFavUseCaseImp(
    private val countryListRepository: CountryListRepository
) : UpdateCountryFavUseCase {
    override suspend fun execute(isFav: Boolean, countryName: String) {
        countryListRepository.updateCountryFav(isFav, countryName)
    }
}
