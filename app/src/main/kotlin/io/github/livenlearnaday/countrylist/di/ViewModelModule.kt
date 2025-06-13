package io.github.livenlearnaday.presentation.di

import io.github.livenlearnaday.domain.countrylist.usecase.ClearAllCountriesFavUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.DeleteAllCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromApiUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromDbUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesSearchedUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountryFromDbByNameUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.SaveCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import io.github.livenlearnaday.presentation.countrydetail.CountryDetailViewModel
import io.github.livenlearnaday.presentation.countrylist.CountryListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel<CountryListViewModel> {
        CountryListViewModel(
            get<FetchCountriesFromApiUseCase>(),
            get<FetchCountriesFromDbUseCase>(),
            get<SaveCountriesUseCase>(),
            get<UpdateCountryFavUseCase>(),
            get<FetchCountriesSearchedUseCase>(),
            get<ClearAllCountriesFavUseCase>(),
            get<DeleteAllCountriesUseCase>()
        )
    }

    viewModel<CountryDetailViewModel> { (countryNameArg: String) ->
        CountryDetailViewModel(
            countryNameArg = countryNameArg,
            get<FetchCountryFromDbByNameUseCase>(),
            get<UpdateCountryFavUseCase>()
        )
    }
}
