package io.github.livenlearnaday.countrylist

import android.app.Application
import io.github.livenlearnaday.data.di.localCacheModule
import io.github.livenlearnaday.data.di.networkModule
import io.github.livenlearnaday.domain.di.countryListUseCaseModule
import io.github.livenlearnaday.presentation.di.countryDetailModule
import io.github.livenlearnaday.presentation.di.countryListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                networkModule,
                localCacheModule,
                countryListUseCaseModule,
                countryListModule,
                countryDetailModule
            )
        }
    }
}
