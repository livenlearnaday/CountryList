package io.github.livenlearnaday.data.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import io.github.livenlearnaday.data.local.CountryListLocalDataSource
import io.github.livenlearnaday.data.local.imp.CountryListLocalDataSourceImp
import io.github.livenlearnaday.data.local_db.CountryDao
import io.github.livenlearnaday.data.local_db.MyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun provideDataBase(application: Application): MyDatabase =
    Room.databaseBuilder(
        application,
        MyDatabase::class.java,
        "app_db"
    ).
    fallbackToDestructiveMigration().build()

fun provideDao(database: MyDatabase): CountryDao = database.countryDao()


val localCacheModule = module {

    single { provideDataBase(get()) }
    single { provideDao(get()) }

    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "secret_shared_prefs",
            MasterKey(androidApplication())
        )
    }

    factory<CountryListLocalDataSource> {
        CountryListLocalDataSourceImp(get<CountryDao>())
    }
}