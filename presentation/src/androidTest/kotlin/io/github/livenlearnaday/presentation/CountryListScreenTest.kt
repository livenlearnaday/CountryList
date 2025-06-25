package io.github.livenlearnaday.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.presentation.countrylist.CountryListScreen
import io.github.livenlearnaday.presentation.countrylist.CountryListState
import java.util.UUID
import org.junit.Rule
import org.junit.Test

class CountryListScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun should_show_countrylist_contents() {
        // Arrange
        rule.setContent {
            CountryListScreen(
                countryListState = CountryListState(countryItems = createCountryItems()),
                onCountryItemClicked = { },
                onCountryListAction = { },
                onBackPressed = { }
            )
        }

        // Act & Assert
        rule.onNodeWithTag("countrylist").assertExists()
    }
}

private fun createCountryItems(): List<CountryModel> = sequence<CountryModel> {
    CountryModel(
        name = UUID.randomUUID().toString(),
        capital = UUID.randomUUID().toString(),
        flag = UUID.randomUUID().toString()
    )
}.take(5).toList()
