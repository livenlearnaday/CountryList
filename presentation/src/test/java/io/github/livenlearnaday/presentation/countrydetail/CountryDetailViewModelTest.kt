package io.github.livenlearnaday.presentation.countrydetail

import com.google.common.truth.Truth.assertThat
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountryFromDbByNameUseCase
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

class CountryDetailViewModelTest {
    private lateinit var viewModel: CountryDetailViewModel
    private lateinit var country: CountryModel
    private val fetchCountryFromDbByNameUseCase: FetchCountryFromDbByNameUseCase = mockk()
    private val updateCountryFavUseCase: UpdateCountryFavUseCase = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        country = CountryMockData.countryThailand
        viewModel = CountryDetailViewModel(
            country.name,
            fetchCountryFromDbByNameUseCase,
            updateCountryFavUseCase
        )
    }

    @Test
    fun `should fetch country data`() = runTest {
        // Arrange
        coEvery { fetchCountryFromDbByNameUseCase.execute(country.name) } returns flowOf(country)

        // Act
        viewModel.countryDetailAction(CountryDetailAction.FetchData)

        // Assert
        assertThat(viewModel.countryDetailState.country.name).isEqualTo(country.name)
        assertThat(viewModel.countryDetailState.country.capital).isEqualTo(country.capital)
        assertThat(viewModel.countryDetailState.country.flag).isEqualTo(country.flag)
    }

    @Test
    fun `should fetch set country fav`() = runTest {
        // Arrange
        coEvery { updateCountryFavUseCase.execute(true, country.name) } returns Unit

        // Act
        viewModel.countryDetailAction(CountryDetailAction.OnCountryFavIconClicked(country))

        // Assert
        assertThat(viewModel.countryDetailState.country.isFav).isEqualTo(true)
    }
}
