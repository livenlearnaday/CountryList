package io.github.livenlearnaday.presentation.countrylist

import com.google.common.truth.Truth.assertThat
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.ClearAllCountriesFavUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.DeleteAllCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromApiUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromDbUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesSearchedUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.SaveCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import io.github.livenlearnaday.presentation.mockdata.CountryMockData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class CountryListViewModelTest {

    private lateinit var viewModel: CountryListViewModel
    private lateinit var countryList: List<CountryModel>
    private val fetchCountriesFromApiUseCase: FetchCountriesFromApiUseCase = mockk()
    private val fetchCountriesFromDbUseCase: FetchCountriesFromDbUseCase = mockk()
    private val saveCountriesUseCase: SaveCountriesUseCase = mockk()
    private val updateCountryFavUseCase: UpdateCountryFavUseCase = mockk()
    private val fetchCountriesSearchedUseCase: FetchCountriesSearchedUseCase = mockk()
    private val clearAllCountriesFavUseCase: ClearAllCountriesFavUseCase = mockk()
    private val deleteAllCountriesUseCase: DeleteAllCountriesUseCase = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        countryList = listOf(CountryMockData.countryThailand)
        viewModel = CountryListViewModel(
            fetchCountriesFromApiUseCase,
            fetchCountriesFromDbUseCase,
            saveCountriesUseCase,
            updateCountryFavUseCase,
            fetchCountriesSearchedUseCase,
            clearAllCountriesFavUseCase,
            deleteAllCountriesUseCase
        )
    }

    @Test
    fun `should fetch countries data from db`() = runTest {
        // Arrange
        coEvery { fetchCountriesFromDbUseCase.execute() } returns flowOf(countryList)

        // Act
        viewModel.countryListAction(CountryListAction.FetchData)

        // Assert
        val countryStateCountry = viewModel.countryListState.countryItems.first()
        val country = countryList.first()

        assertThat(countryStateCountry.name).isEqualTo(country.name)
        assertThat(countryStateCountry.capital).isEqualTo(country.capital)
        assertThat(countryStateCountry.flag).isEqualTo(country.flag)
    }

    @Test
    fun `should fetch from the network`() = runTest {
        // Arrange
        coEvery { fetchCountriesFromDbUseCase.execute() } returns flowOf(emptyList())
        coEvery { fetchCountriesFromApiUseCase.execute() } returns CheckResult.Success(countryList)

        // Act
        viewModel.countryListAction(CountryListAction.FetchData)

        // Assert
        val countryStateCountry = viewModel.countryListState.countryItems.first()
        val country = countryList.first()

        assertThat(countryStateCountry.name).isEqualTo(country.name)
        assertThat(countryStateCountry.capital).isEqualTo(country.capital)
        assertThat(countryStateCountry.flag).isEqualTo(country.flag)
    }

    @Test
    fun `refetch data from network`() = runTest {
        // Arrange
        coEvery { deleteAllCountriesUseCase.execute() } returns Unit
        coEvery { fetchCountriesFromApiUseCase.execute() } returns CheckResult.Success(countryList)

        // Act
        viewModel.countryListAction(CountryListAction.OnFetchCountryListFromApi)

        // Assert
        val countryStateCountry = viewModel.countryListState.countryItems.first()
        val country = countryList.first()

        assertThat(countryStateCountry.name).isEqualTo(country.name)
        assertThat(countryStateCountry.capital).isEqualTo(country.capital)
        assertThat(countryStateCountry.flag).isEqualTo(country.flag)
    }
}
