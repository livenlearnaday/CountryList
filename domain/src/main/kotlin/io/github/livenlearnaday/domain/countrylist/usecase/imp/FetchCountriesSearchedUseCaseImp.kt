package io.github.livenlearnaday.domain.countrylist.usecase.imp

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesSearchedUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow

class FetchCountriesSearchedUseCaseImp(
    private val countryListRepository: CountryListRepository
): FetchCountriesSearchedUseCase {
    override fun execute(query: String): Flow<List<CountryModel>> {
        return countryListRepository.fetchSearchCountries(query)
    }

}