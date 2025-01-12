package io.github.livenlearnaday.data.di


import io.github.livenlearnaday.data.local.CountryListLocalDataSource
import io.github.livenlearnaday.data.network_clients.HttpKtorClient
import io.github.livenlearnaday.data.remote.CountryListRemoteDataSource
import io.github.livenlearnaday.data.remote.imp.CountryListRemoteDataSourceImp
import io.github.livenlearnaday.data.repository.CountryListRepositoryImp
import io.github.livenlearnaday.domain.repository.CountryListRepository
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {

    single<HttpClient> { _ ->
        HttpKtorClient().build()
    }

    factory<CountryListRemoteDataSource> {
        CountryListRemoteDataSourceImp(
            get<HttpClient>()
        )
    }

    factory<CountryListRepository> {
        CountryListRepositoryImp(
            get<CountryListRemoteDataSource>(),
            get<CountryListLocalDataSource>())
    }

}