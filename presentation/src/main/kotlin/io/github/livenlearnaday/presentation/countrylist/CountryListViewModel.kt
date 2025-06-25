package io.github.livenlearnaday.presentation.countrylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.livenlearnaday.domain.CheckResult
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.usecase.ClearAllCountriesFavUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.DeleteAllCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromApiUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesFromDbUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.FetchCountriesSearchedUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.SaveCountriesUseCase
import io.github.livenlearnaday.domain.countrylist.usecase.UpdateCountryFavUseCase
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class CountryListViewModel(
    private val fetchCountriesFromApiUseCase: FetchCountriesFromApiUseCase,
    private val fetchCountriesFromDbUseCase: FetchCountriesFromDbUseCase,
    private val saveCountriesUseCase: SaveCountriesUseCase,
    private val updateCountryFavUseCase: UpdateCountryFavUseCase,
    private val fetchCountriesSearchedUseCase: FetchCountriesSearchedUseCase,
    private val clearAllCountriesFavUseCase: ClearAllCountriesFavUseCase,
    private val deleteAllCountriesUseCase: DeleteAllCountriesUseCase
) : ViewModel() {

    companion object {
        @JvmStatic
        private val TAG = CountryListViewModel::class.java.simpleName
    }

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    var countryListState by mutableStateOf(CountryListState())
        private set

    init {
        CountryListViewModel::class.simpleName?.let {
            Timber.tag(it)
        }
    }

    private fun fetchCountryList() {
        viewModelScope.launch(defaultExceptionHandler) {
            if (fetchCountriesFromDbUseCase.execute().first().isEmpty()) {
                fetchCountryListFromApi()
            }

            fetchCountriesFromDbUseCase.execute()
                .onEach { listOfCountries: List<CountryModel> ->
                    countryListState = countryListState.copy(
                        countryItems = listOfCountries.sortedBy { it.name },
                        isLoading = false
                    )
                }
                .launchIn(this)
        }
    }

    private fun fetchCountryListFromDb() {
        fetchCountriesFromDbUseCase.execute()
            .onEach { listOfCountries: List<CountryModel> ->
                countryListState = countryListState.copy(
                    countryItems = listOfCountries.sortedBy { it.name },
                    isLoading = false
                )
            }
    }

    private suspend fun fetchCountryListFromApi() {
        when (val apiResponse = fetchCountriesFromApiUseCase.execute()) {
            is CheckResult.Success -> {
                saveCountriesUseCase.execute(countries = apiResponse.data)
                updateTimeStamp()
                updateIsLoading(false)
            }

            is CheckResult.Failure -> {
                updateIsLoading(false)

                Timber.d(
                    "$TAG %s %s",
                    apiResponse.exceptionError,
                    apiResponse.responseError?.errors?.first()?.message

                )
            }
        }
    }

    fun countryListAction(countryListAction: CountryListAction) {
        when (countryListAction) {
            is CountryListAction.OnFetchCountryListFromApi -> {
                refetchDataFromApi()
                resetMenuSettings()
            }

            is CountryListAction.OnCountryItemFavClicked -> {
                updateCountryIsFav(countryListAction.countryItem)
            }

            is CountryListAction.OnMoreIconClicked -> {
                updateShowMenu(true)
            }

            is CountryListAction.OnSelectMenuItem -> {
                updateCustomMenuItem(countryListAction.menuItem)
                when (countryListAction.menuItem) {
                    CustomMenuItem.FetchFromNetwork,
                    CustomMenuItem.ClearAllFavs -> updateShowMenuWarning(true)

                    CustomMenuItem.ShowAllFavs -> {}

                    else -> {
                        resetMenuSettings()
                    }
                }
            }

            is CountryListAction.OnDismissMenu -> {
                resetMenuSettings()
            }

            is CountryListAction.OnDismissDialog -> {
                resetMenuSettings()
            }

            is CountryListAction.OnSearchIconClicked -> {
                updateShowSearchBar(!countryListState.showSearchBar)
            }

            is CountryListAction.OnSearchQuerySubmit -> {
                searchDbByText(countryListAction.query)
            }

            CountryListAction.OnClearAllFavs -> {
                clearAllFavs()
                resetMenuSettings()
                fetchCountryListFromDb()
            }

            CountryListAction.OnShowFavs -> {
                countryListState = countryListState.copy(
                    showAllFav = true,
                    countryItems = countryListState.countryItems.filter { it.isFav }
                )
                resetMenuSettings()
            }

            CountryListAction.OnRefreshListScreen -> {
                repopulateCountryItems()
            }

            CountryListAction.OnExitSearchMode -> {
                countryListState = countryListState.copy(
                    showSearchBar = false
                )
            }

            CountryListAction.FetchData -> {
                fetchCountryList()
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun updateTimeStamp() {
        val now: Instant = Clock.System.now() // Current instant in UTC
        val nowInUTCMilliSeconds = now.toEpochMilliseconds().div(1000)
        countryListState = countryListState.copy(
            fetchDataUnixTimeStampInSeconds = nowInUTCMilliSeconds.toString()
        )
    }

    private fun updateCountryIsFav(country: CountryModel) {
        viewModelScope.launch(defaultExceptionHandler) {
            updateCountryFavUseCase.execute(!country.isFav, country.name)
            val updatedCountryItems = updateLocalCountryFav(countryListState.countryItems, country)

            countryListState = countryListState.copy(
                countryItems = updatedCountryItems
            )
        }
    }

    private fun updateLocalCountryFav(countryItems: List<CountryModel>, country: CountryModel): List<CountryModel> {
        countryItems.forEachIndexed { index, item ->
            if (item.id == country.id) {
                countryItems[index].copy(
                    isFav = !country.isFav
                )
            }
            return countryItems
        }

        return countryItems
    }

    private fun updateShowMenu(isShow: Boolean) {
        countryListState = countryListState.copy(
            showMenu = isShow
        )
    }

    private fun updateShowMenuWarning(isShow: Boolean) {
        countryListState = countryListState.copy(
            showMenuWarning = isShow
        )
    }

    private fun updateCustomMenuItem(customMenuItem: CustomMenuItem) {
        countryListState = countryListState.copy(
            customMenuItem = customMenuItem
        )
    }

    private fun updateShowSearchBar(isShow: Boolean) {
        countryListState = countryListState.copy(
            showSearchBar = isShow
        )
    }

    private fun searchDbByText(query: String) {
        viewModelScope.launch(defaultExceptionHandler) {
            fetchCountriesSearchedUseCase.execute(query)
                .onEach { listOfCountries: List<CountryModel> ->
                    countryListState = countryListState.copy(
                        countryItems = listOfCountries.sortedBy { it.name }
                    )
                }
                .launchIn(this)
        }
    }

    private fun clearAllFavs() {
        viewModelScope.launch(defaultExceptionHandler) {
            resetCountryItems()
            val clearFavDeferred = async { clearAllCountriesFavUseCase.execute() }
            clearFavDeferred.await()

            fetchCountriesFromDbUseCase.execute()
                .onEach { listOfCountries: List<CountryModel> ->
                    countryListState = countryListState.copy(
                        countryItems = listOfCountries.sortedBy { it.name }
                    )
                }
                .launchIn(this)
        }
    }

    private fun refetchDataFromApi() {
        viewModelScope.launch(defaultExceptionHandler) {
            val clearDataDeferred = async { deleteAllCountriesUseCase.execute() }
            clearDataDeferred.await()
            updateIsLoading(true)
            fetchCountryListFromApi()
        }
    }

    private fun resetMenuSettings() {
        updateShowMenu(false)
        updateShowMenuWarning(false)
        updateCustomMenuItem(CustomMenuItem.None)
    }

    private fun resetCountryItems() {
        countryListState = countryListState.copy(
            countryItems = emptyList()
        )
    }

    fun repopulateCountryItems() {
        countryListState = countryListState.copy(
            countryItems = emptyList(),
            showSearchBar = false,
            showAllFav = false,
            isLoading = true
        )
        fetchCountryList()
    }

    private fun updateIsLoading(isLoading: Boolean) {
        countryListState = countryListState.copy(
            isLoading = isLoading
        )
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("OnCleared ${CountryListViewModel::class.simpleName}")
    }
}
