package io.github.livenlearnaday.countrylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.livenlearnaday.countrylist.navigation.AppNavigation
import io.github.livenlearnaday.countrylist.ui.theme.CountryListTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CountryListTheme {
                AppNavigation()
            }
        }
    }
}
