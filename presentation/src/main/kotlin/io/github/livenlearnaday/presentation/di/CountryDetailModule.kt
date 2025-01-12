package io.github.livenlearnaday.presentation.di

import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountryFromDbByNameUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import io.github.livenlearnaday.presentation.countrydetail.CountryDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val countryDetailModule = module {
    viewModel<CountryDetailViewModel> {  (countryNameArg: String) ->
        CountryDetailViewModel(
            countryNameArg = countryNameArg,
            get<FetchCountryFromDbByNameUseCase>(),
            get<UpdateCountryFavUseCase>()
        )
    }
}