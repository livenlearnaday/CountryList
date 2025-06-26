package io.github.livenlearnaday.presentation.countrylist

import com.google.common.truth.Truth.assertThat
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.countrylist.usecase.ClearAllCountriesFavUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.DeleteAllCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromApiUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromDbUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesSearchedUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.SaveCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import io.github.livenlearnaday.presentation.mockdata.CountryMockData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountryListViewModelTest {

    private lateinit var viewModel: CountryListViewModel

    @MockK
    private lateinit var fetchCountriesFromApiUseCase: FetchCountriesFromApiUseCase

    @MockK
    private lateinit var fetchCountriesFromDbUseCase: FetchCountriesFromDbUseCase

    @MockK
    private lateinit var saveCountriesUseCase: SaveCountriesUseCase

    @MockK
    private lateinit var updateCountryFavUseCase: UpdateCountryFavUseCase

    @MockK
    private lateinit var fetchCountriesSearchedUseCase: FetchCountriesSearchedUseCase

    @MockK
    private lateinit var clearAllCountriesFavUseCase: ClearAllCountriesFavUseCase

    @MockK
    private lateinit var deleteAllCountriesUseCase: DeleteAllCountriesUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `should fetch countries data from db`() {
        // Arrange
        val countryList = listOf(CountryMockData.countryThailand)
        every { fetchCountriesFromDbUseCase.execute() } returns flowOf(countryList)

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
    fun `should fetch from the network`() {
        // Arrange
        val countryList = listOf(CountryMockData.countryThailand)
        every { fetchCountriesFromDbUseCase.execute() } returns flowOf(emptyList())
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
    fun `refetch data from network`() {
        // Arrange
        val countryList = listOf(CountryMockData.countryThailand)
        coEvery { deleteAllCountriesUseCase.execute() } returns Unit
        coEvery { fetchCountriesFromApiUseCase.execute() } returns CheckResult.Success(countryList)
        coEvery { saveCountriesUseCase.execute(any()) } returns Unit

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
