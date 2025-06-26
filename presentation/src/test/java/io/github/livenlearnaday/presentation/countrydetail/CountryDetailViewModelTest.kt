package io.github.livenlearnaday.presentation.countrydetail

import com.google.common.truth.Truth.assertThat
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountryFromDbByNameUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import io.github.livenlearnaday.presentation.mockdata.CountryMockData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountryDetailViewModelTest {
    private lateinit var viewModel: CountryDetailViewModel

    @MockK
    private lateinit var fetchCountryFromDbByNameUseCase: FetchCountryFromDbByNameUseCase

    @MockK
    private lateinit var updateCountryFavUseCase: UpdateCountryFavUseCase

    private lateinit var country: CountryModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        country = CountryMockData.countryThailand
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = CountryDetailViewModel(
            country.name,
            fetchCountryFromDbByNameUseCase,
            updateCountryFavUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch country data on success`() {
        // Arrange
        every { fetchCountryFromDbByNameUseCase.execute(country.name) } returns flowOf(country)

        // Act
        viewModel.countryDetailAction(CountryDetailAction.FetchData)

        // Assert
        if (!viewModel.countryDetailState.isLoading) {
            val result = viewModel.countryDetailState.country
            assertThat(result.name).isEqualTo(country.name)
            assertThat(result.capital).isEqualTo(country.capital)
            assertThat(result.flag).isEqualTo(country.flag)
        }
    }

    @Test
    fun `set country fav should return true`() {
        // Arrange
        coEvery { updateCountryFavUseCase.execute(true, country.name) } returns Unit

        // Act
        viewModel.countryDetailAction(CountryDetailAction.OnCountryFavIconClicked(country))

        // Assert
        val result = viewModel.countryDetailState.country
        assertThat(result.isFav).isEqualTo(true)
    }
}
