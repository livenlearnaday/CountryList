package io.github.livenlearnaday.presentation.countrydetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountryFromDbByNameUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class CountryDetailViewModel(
    private val countryNameArg: String,
    private val fetchCountryFromDbByNameUseCase: FetchCountryFromDbByNameUseCase,
    private val updateCountryFavUseCase: UpdateCountryFavUseCase
) : ViewModel() {

    companion object {
        @JvmStatic
        private val TAG = CountryDetailViewModel::class.java.simpleName
    }

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    var countryDetailState by mutableStateOf(CountryDetailState())
        private set

    init {
        CountryDetailViewModel::class.simpleName?.let {
            Timber.tag(it)
        }

        Timber.d("log $TAG countryNameArg: $countryNameArg")
    }

    private fun fetchCountry(name: String) {
        viewModelScope.launch(defaultExceptionHandler) {
            Timber.d("log $TAG fetchCountry name: $name")
            fetchCountryFromDbByNameUseCase.execute(name).collect { country ->
                updateCountryModel(country)
            }
        }
    }

    private fun updateCountryModel(country: CountryModel) {
        Timber.d("log, $TAG updateCountryModel country.name:${country.name}")
        Timber.d("log, $TAG updateCountryModel country.flag:${country.flag}")

        countryDetailState = countryDetailState.copy(
            country = country
        )
    }

    fun countryDetailAction(countryDetailAction: CountryDetailAction) {
        when (countryDetailAction) {
            is CountryDetailAction.OnCountryFavIconClicked -> {
                val isFav = !countryDetailAction.country.isFav
                val updatedCountry = countryDetailState.country.copy(isFav = isFav)
                countryDetailState = countryDetailState.copy(
                    country = updatedCountry
                )
                updateCountryIsFav(updatedCountry)
            }

            CountryDetailAction.FetchData -> {
                fetchCountry(countryNameArg)
            }
        }
    }

    private fun updateCountryIsFav(country: CountryModel) {
        viewModelScope.launch(defaultExceptionHandler) {
            updateCountryFavUseCase.execute(country.isFav, country.name)
        }
    }
}
