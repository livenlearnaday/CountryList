package io.github.livenlearnaday.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.model.LanguageModel
import io.github.livenlearnaday.presentation.countrydetail.CountryDetailScreen
import io.github.livenlearnaday.presentation.countrydetail.CountryDetailState
import org.junit.Rule
import org.junit.Test

class CountryDetailScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun should_show_country_detail_contents() {
        // Arrange
        val countryModel = CountryModel(
            name = "Thailand",
            capital = "Bangkok",
            languages = listOf(LanguageModel(name = "Thai")),
            region = "Southeast Asia",
            subregion = "Indochinese Peninsula",
            callingCodes = listOf("66")
        )
        rule.setContent {
            CountryDetailScreen(
                onBackPressed = {},
                countryDetailState = CountryDetailState(
                    countryName = countryModel.name,
                    country = countryModel
                ),
                onCountryDetailAction = {}
            )
        }

        // Act & Assert
        rule.onNodeWithText(countryModel.name).assertExists()
        rule.onNodeWithText(countryModel.capital).assertExists()
        rule.onNodeWithText(countryModel.languages.first().name).assertExists()
        rule.onNodeWithText(countryModel.region).assertExists()
        rule.onNodeWithText(countryModel.subregion).assertExists()
        rule.onNodeWithText("+${countryModel.callingCodes.first()}").assertExists()
    }
}
