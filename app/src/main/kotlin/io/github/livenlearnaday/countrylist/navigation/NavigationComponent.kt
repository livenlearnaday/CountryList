package io.github.livenlearnaday.countrylist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.livenlearnaday.presentation.countrydetail.CountryDetailScreen
import io.github.livenlearnaday.presentation.countrydetail.CountryDetailViewModel
import io.github.livenlearnaday.presentation.countrylist.CountryListAction
import io.github.livenlearnaday.presentation.countrylist.CountryListScreen
import io.github.livenlearnaday.presentation.countrylist.CountryListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.CountryList
    ) {
        composable<NavigationRoute.CountryList> {
            val countryListViewModel = koinViewModel<CountryListViewModel>()
            val countryListState = countryListViewModel.countryListState
            CountryListScreen(
                onCountryItemClicked = { item ->
                    navController.navigate(NavigationRoute.CountryDetail(item.name))
                },
                countryListState = countryListState,
                onCountryListAction = countryListViewModel::countryListAction,
                onBackPressed = {
                    when {
                        countryListState.showSearchBar -> {
                            countryListViewModel.countryListAction(CountryListAction.OnExitSearchMode)
                        }

                        else -> navController.popBackStack()
                    }
                }
            )
        }
        composable<NavigationRoute.CountryDetail> { backStackEntry ->
            val countryNameArg =
                backStackEntry.toRoute<NavigationRoute.CountryDetail>().countryNameArg
            val countryDetailViewModel =
                koinViewModel<CountryDetailViewModel> { parametersOf(countryNameArg) }
            val countryDetailState = countryDetailViewModel.countryDetailState
            CountryDetailScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                countryDetailState = countryDetailState,
                onCountryDetailAction = countryDetailViewModel::countryDetailAction

            )
        }
    }
}
