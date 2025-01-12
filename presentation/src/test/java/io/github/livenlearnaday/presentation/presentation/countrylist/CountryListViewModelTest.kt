package io.github.livenlearnaday.presentation.countrylist

import com.google.common.truth.Truth.assertThat
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromApiUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import java.util.UUID

class CountryListViewModelTest {
    private val fetchCountriesFromApiUseCase: FetchCountriesFromApiUseCase = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `should fetch from the network`() = runTest {
        // Arrange
        val name = UUID.randomUUID().toString()
        val capital = UUID.randomUUID().toString()
        val flag = UUID.randomUUID().toString()
        val countryListModel = listOf(CountryModel(1, name, capital, flag))

        val countryListViewModel = CountryListViewModel(fetchCountriesFromApiUseCase, mockk(), mockk(), mockk(), mockk(), mockk(), mockk())
        coEvery{ fetchCountriesFromApiUseCase.execute() } returns CheckResult.Success(countryListModel)

        // Act
        countryListViewModel.countryListAction(CountryListAction.OnFetchCountryListFromApi)

        // Assert

        assertThat(countryListViewModel.countryListState.countryItems.first().name).isEqualTo(name)
        assertThat(countryListViewModel.countryListState.countryItems.first().capital).isEqualTo(capital)
        assertThat(countryListViewModel.countryListState.countryItems.first().flag).isEqualTo(flag)
    }
}