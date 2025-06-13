package io.github.livenlearnaday.domain.di

import io.github.livenlearnaday.domain.countrylist.usecase.ClearAllCountriesFavUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.DeleteAllCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromApiUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromDbUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesSearchedUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountryFromDbByNameUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.SaveCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.imp.ClearAllCountriesFavUseCaseImp
import io.github.livenlearnaday.domain.countrylist.usecase.imp.FetchCountriesFromDbUseCaseImp
import io.github.livenlearnaday.domain.countrylist.usecase.imp.FetchCountriesSearchedUseCaseImp
import io.github.livenlearnaday.domain.countrylist.usecase.imp.FetchCountryFromDbByNameUseCaseImp
import io.github.livenlearnaday.domain.countrylist.usecase.imp.SaveCountriesUseCaseImp
import io.github.livenlearnaday.domain.countrylist.usecase.imp.UpdateCountryFavUseCaseImp
import io.github.livenlearnaday.domain.countrylist.usecase.imp.UpdateCountryUseCaseImp
import io.github.livenlearnaday.domain.repository.CountryListRepository
import io.github.livenlearnaday.domain.survey.usecases.imp.DeleteAllCountriesUseCaseImp
import io.github.livenlearnaday.domain.survey.usecases.imp.FetchCountriesFromApiUseCaseImp
import org.koin.dsl.module

val countryListUseCaseModule = module {

    factory<ClearAllCountriesFavUseCase> {
        ClearAllCountriesFavUseCaseImp(get<CountryListRepository>())
    }

    factory<DeleteAllCountriesUseCase> {
        DeleteAllCountriesUseCaseImp(get<CountryListRepository>())
    }

    factory<FetchCountriesFromApiUseCase> {
        FetchCountriesFromApiUseCaseImp(get<CountryListRepository>())
    }

    factory<FetchCountriesFromDbUseCase> {
        FetchCountriesFromDbUseCaseImp(get<CountryListRepository>())
    }

    factory<FetchCountriesSearchedUseCase> {
        FetchCountriesSearchedUseCaseImp(get<CountryListRepository>())
    }

    factory<SaveCountriesUseCase> {
        SaveCountriesUseCaseImp(get<CountryListRepository>())
    }

    factory<UpdateCountryFavUseCase> {
        UpdateCountryFavUseCaseImp(get<CountryListRepository>())
    }

    factory<UpdateCountryUseCase> {
        UpdateCountryUseCaseImp(get<CountryListRepository>())
    }

    factory<FetchCountryFromDbByNameUseCase> {
        FetchCountryFromDbByNameUseCaseImp(get<CountryListRepository>())
    }
}
