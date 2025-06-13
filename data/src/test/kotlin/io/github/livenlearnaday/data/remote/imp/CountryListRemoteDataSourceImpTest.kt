package io.github.livenlearnaday.data.remote.imp

import com.google.common.truth.Truth.assertThat
import io.github.livenlearnaday.data.countrylist.CountryDto
import io.github.livenlearnaday.data.remote.CountryListRemoteDataSource
import io.github.livenlearnaday.domain.CheckResult
import io.mockk.coEvery
import io.mockk.mockk
import java.util.UUID
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CountryListRemoteDataSourceImpTest {

    private var countryListRemoteDataSource: CountryListRemoteDataSource = mockk()

    @Test
    fun `should fetch country list`() = runTest {
        // Arrange
        val name = UUID.randomUUID().toString()
        val capital = UUID.randomUUID().toString()
        val flag = UUID.randomUUID().toString()
        val region = UUID.randomUUID().toString()
        val subregion = UUID.randomUUID().toString()

        val countryList = listOf(
            CountryDto(name, capital, region, subregion, flag, emptyList(), listOf("+66"))
        )

        coEvery { countryListRemoteDataSource.fetchCountries() } returns (CheckResult.Success(countryList))

        // Act
        val actual = countryListRemoteDataSource.fetchCountries() as CheckResult.Success<*>

        // Assert
        assertThat(actual.data).isEqualTo(countryList)
    }
}
