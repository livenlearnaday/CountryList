package io.github.livenlearnaday.domain.countrylist.usecase.imp

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountryFromDbByNameUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow

class FetchCountryFromDbByNameUseCaseImp  (
    private val countryListRepository: CountryListRepository
): FetchCountryFromDbByNameUseCase {
    override fun execute(name: String): Flow<CountryModel> {
        return countryListRepository.fetchCountryFromDbByName(name)
    }

}