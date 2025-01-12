package io.github.livenlearnaday.presentation.countrylist


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.presentation.R
import io.github.livenlearnaday.presentation.components.CommonAlertDialog
import io.github.livenlearnaday.presentation.components.CustomImage
import io.github.livenlearnaday.presentation.components.CustomTopAppBar
import io.github.livenlearnaday.presentation.components.FavToggleButton
import io.github.livenlearnaday.presentation.ui.theme.CountryListTheme

@Composable
fun CountryListScreen(
    countryListState: CountryListState,
    onCountryListAction: (CountryListAction) -> Unit,
    onCountryItemClicked: (country: CountryModel) -> Unit,
    modifier: Modifier = Modifier
) {

    if (countryListState.showMenuWarning) {
        CommonAlertDialog(
            onClose = {
                onCountryListAction(CountryListAction.OnDismissDialog)
            },
            onConfirm = {
                when (countryListState.customMenuItem) {
                    CustomMenuItem.FetchFromNetwork -> {
                        onCountryListAction(CountryListAction.OnFetchCountryListFromApi)
                    }

                    CustomMenuItem.ClearAllFavs -> {
                        onCountryListAction(CountryListAction.OnClearAllFavs)
                    }

                    else -> {
                        onCountryListAction(CountryListAction.OnDismissDialog)
                    }
                }
            },
            onDismiss = {
                onCountryListAction(CountryListAction.OnDismissDialog)
            },
            dialogText = countryListState.customMenuItem.warningTextRes?.let { stringResource(it) }
                ?: ""
        )
    }

    when (countryListState.customMenuItem) {
        is CustomMenuItem.ShowAllFavs -> {
            onCountryListAction(CountryListAction.OnShowFavs)
        }
        else -> {}
    }


    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.country_list),
                isListScreen = true,
                showSearchBar = countryListState.showSearchBar,
                showAllFav = countryListState.showAllFav,
                onSearchIconClicked = {
                    onCountryListAction(CountryListAction.OnSearchIconClicked)
                },
                onSearchQuerySubmit = {
                    onCountryListAction(CountryListAction.OnSearchQuerySubmit(it))
                },
                onMoreIconClicked = {
                    onCountryListAction(CountryListAction.OnMoreIconClicked)
                },
                isMenuExpanded = countryListState.showMenu,
                onMenuItemClicked = {
                    onCountryListAction(CountryListAction.OnSelectMenuItem(it))
                },
                onDismissMenu = {
                    onCountryListAction(CountryListAction.OnDismissMenu)
                },
                onBackPressed = {
                    onCountryListAction(CountryListAction.OnRefreshListScreen)
                },
            )
        },
        content = { innerPadding ->

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopStart
            ) {

                if (countryListState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .testTag("countrylist")
                            .padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(countryListState.countryItems) { index, item ->
                            CountryItemScreen(
                                country = item,
                                onItemClicked = {
                                    onCountryItemClicked(item)
                                },
                                onFavClicked = {
                                    onCountryListAction(
                                        CountryListAction.OnCountryItemFavClicked(
                                            item
                                        )
                                    )
                                }
                            )
                            if (index < countryListState.countryItems.lastIndex) {
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CountryItemScreen(
    country: CountryModel,
    onItemClicked: (country: CountryModel) -> Unit,
    onFavClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                onItemClicked(country)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        CustomImage(
            flagUrlString = country.flag,
            modifier = Modifier
                .fillMaxSize()
                .size(60.dp)
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .padding(start = 15.dp)
                .weight(3f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = country.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = country.capital,
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        FavToggleButton(
            modifier = Modifier
                .padding(end = 15.dp)
                .weight(0.5f),
            isFav = country.isFav,
            onFavCLicked = { onFavClicked() }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCountryListScreen() {
    CountryListTheme {
        CountryListScreen(
            onCountryItemClicked = {},
            onCountryListAction = {},
            countryListState = CountryListState(
                countryItems = listOf(
                    CountryModel(
                        name = "Afghanistan",
                        capital = "Kabu",
                        flag = "https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_the_Taliban.svg"
                    ),
                    CountryModel(
                        name = "Albania",
                        capital = "Tirana",
                        flag = "https://flagcdn.com/al.svg",
                        isFav = true
                    ),
                ),
                showSearchBar = true
            )
        )
    }
}

