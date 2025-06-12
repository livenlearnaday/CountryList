package io.github.livenlearnaday.data.di

import android.app.Application
import androidx.room.Room
import io.github.livenlearnaday.data.local.CountryListLocalDataSource
import io.github.livenlearnaday.data.local.imp.CountryListLocalDataSourceImp
import io.github.livenlearnaday.data.local_db.CountryDao
import io.github.livenlearnaday.data.local_db.MyDatabase
import org.koin.dsl.module

fun provideDataBase(application: Application): MyDatabase =
    Room.databaseBuilder(
        application,
        MyDatabase::class.java,
        "app_db"
    ).fallbackToDestructiveMigration(false).build()

fun provideDao(database: MyDatabase): CountryDao = database.countryDao()


val localCacheModule = module {

    single { provideDataBase(get()) }
    single { provideDao(get()) }


    factory<CountryListLocalDataSource> {
        CountryListLocalDataSourceImp(get<CountryDao>())
    }
}