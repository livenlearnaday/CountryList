package io.github.livenlearnaday.domain.countrylist.usecase.imp

import com.google.common.truth.Truth.assertThat
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.repository.CountryListRepository
import io.github.livenlearnaday.domain.survey.usecases.imp.FetchCountriesFromApiUseCaseImp
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.UUID


class FetchCountriesFromApiImpTest {

    private val countryListRepository: CountryListRepository = mockk()

    @Test
    fun should_return_countryList_when_success() = runTest{
        // Arrange
        val flag = UUID.randomUUID().toString()
        val countryList =  listOf(
                CountryModel( 1, "Thailand", "Bangkok", "region", "subregion", flag, emptyList(), listOf("+66"))
            )
        coEvery { countryListRepository.fetchCountriesFromApi() } returns CheckResult.Success(countryList)
        val fetchCountriesFromApiUseCase = FetchCountriesFromApiUseCaseImp(countryListRepository)

        // Act
        val result = fetchCountriesFromApiUseCase.execute()

        // Assert
        assertThat(result).isEqualTo(countryList)

    }
}