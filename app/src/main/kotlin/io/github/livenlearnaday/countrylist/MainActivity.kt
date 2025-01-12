package io.github.livenlearnaday.countrylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.livenlearnaday.countrylist.navigation.AppNavigation
import io.github.livenlearnaday.countrylist.ui.theme.CountryListTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinContext() {
                CountryListTheme {
                    AppNavigation()
                    }
                }
            }

    }
}
