package io.github.livenlearnaday.domain.countrylist.usecase.imp

import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromDbUseCase
import io.github.livenlearnaday.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow

class FetchCountriesFromDbUseCaseImp(
    private val countryListRepository: CountryListRepository
) : FetchCountriesFromDbUseCase {
    override fun execute(): Flow<List<CountryModel>> {
        return countryListRepository.fetchCountriesFromDb()
    }
}