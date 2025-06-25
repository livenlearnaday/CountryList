package io.github.livenlearnaday.presentation.countrydetail

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import io.github.livenlearnaday.domain.countrylist.model.CountryModel
import io.github.livenlearnaday.domain.countrylist.model.LanguageModel
import io.github.livenlearnaday.presentation.R
import io.github.livenlearnaday.presentation.components.CallingCodeCard
import io.github.livenlearnaday.presentation.components.CustomImage
import io.github.livenlearnaday.presentation.components.CustomTopAppBar
import io.github.livenlearnaday.presentation.components.FavToggleButton

@Composable
fun CountryDetailScreen(
    onBackPressed: () -> Unit,
    countryDetailState: CountryDetailState,
    onCountryDetailAction: (CountryDetailAction) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        onCountryDetailAction(CountryDetailAction.FetchData)
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.country_detail),
                onSearchIconClicked = { },
                onSearchQuerySubmit = { },
                onMoreIconClicked = { },
                onMenuItemClicked = { },
                onDismissMenu = { },
                onBackPressed = {
                    onBackPressed()
                }
            )
        },
        content = { innerPadding ->

            ScrollableColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                country = countryDetailState.country,
                onFavClicked = {
                    onCountryDetailAction(CountryDetailAction.OnCountryFavIconClicked(it))
                }
            )
        }
    )
}

@Composable
fun ScrollableColumn(
    modifier: Modifier = Modifier,
    country: CountryModel,
    onFavClicked: (country: CountryModel) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        ConstraintLayout {
            val (
                favButtonLayout,
                flagImage,
                countryName,
                countryCapitalTitle,
                countryCapitalText,
                countryRegionTitle,
                countryRegionText,
                countrySubRegionTitle,
                countrySubRegionText,
                countryLanguageTitle,
                countryLaguagesLayout,
                countryCallingcodesTitle,
                countryCallingCodeLayout
            ) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(favButtonLayout) {
                        top.linkTo(parent.top, margin = 30.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end, margin = 50.dp)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End

            ) {
                FavToggleButton(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    isFav = country.isFav,
                    onFavCLicked = { onFavClicked(country) }
                )
            }

            CustomImage(
                flagUrlString = country.flag,
                modifier = Modifier
                    .fillMaxSize()
                    .size(180.dp)
                    .padding(10.dp)
                    .constrainAs(flagImage) {
                        top.linkTo(favButtonLayout.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = country.name,
                Modifier
                    .constrainAs(countryName) {
                        top.linkTo(flagImage.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = stringResource(R.string.title_capital),
                Modifier
                    .constrainAs(countryCapitalTitle) {
                        top.linkTo(countryName.bottom, margin = 10.dp)
                        start.linkTo(parent.start, margin = 60.dp)
                    },
                fontWeight = FontWeight.Bold
            )

            Text(
                text = country.capital,
                Modifier
                    .constrainAs(countryCapitalText) {
                        top.linkTo(countryCapitalTitle.top)
                        bottom.linkTo(countryCapitalTitle.bottom)
                        end.linkTo(parent.end, margin = 60.dp)
                    }
            )

            Text(
                text = stringResource(R.string.title_region),
                Modifier
                    .constrainAs(countryRegionTitle) {
                        top.linkTo(countryCapitalTitle.bottom, margin = 10.dp)
                        start.linkTo(countryCapitalTitle.start)
                    },
                fontWeight = FontWeight.Bold
            )

            Text(
                text = country.region,
                Modifier
                    .constrainAs(countryRegionText) {
                        top.linkTo(countryRegionTitle.top)
                        bottom.linkTo(countryRegionTitle.bottom)
                        end.linkTo(parent.end, margin = 60.dp)
                    }
            )

            Text(
                text = stringResource(R.string.title_subregion),
                Modifier
                    .constrainAs(countrySubRegionTitle) {
                        top.linkTo(countryRegionTitle.bottom, margin = 10.dp)
                        start.linkTo(countryRegionTitle.start)
                    },
                fontWeight = FontWeight.Bold
            )

            Text(
                text = country.subregion,
                Modifier
                    .constrainAs(countrySubRegionText) {
                        top.linkTo(countrySubRegionTitle.top)
                        bottom.linkTo(countrySubRegionTitle.bottom)
                        end.linkTo(parent.end, margin = 60.dp)
                    }
            )

            Text(
                text = stringResource(R.string.title_languages),
                Modifier
                    .constrainAs(countryLanguageTitle) {
                        top.linkTo(countrySubRegionTitle.bottom, margin = 10.dp)
                        start.linkTo(countrySubRegionTitle.start)
                    },
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .constrainAs(countryLaguagesLayout) {
                        top.linkTo(countryLanguageTitle.top)
                        end.linkTo(parent.end, margin = 60.dp)
                        bottom.linkTo(countryCallingcodesTitle.top, margin = 20.dp)
                    },
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                country.languages.forEachIndexed { _, languageModel ->

                    if (languageModel.name.isNotEmpty()) {
                        Text(
                            text = languageModel.name
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.title_calling_codes),
                Modifier
                    .padding(top = 10.dp)
                    .constrainAs(countryCallingcodesTitle) {
                        top.linkTo(countryLaguagesLayout.bottom, margin = 10.dp)
                        start.linkTo(countryLanguageTitle.start)
                    },
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .constrainAs(countryCallingCodeLayout) {
                        top.linkTo(countryCallingcodesTitle.bottom)
                        start.linkTo(parent.start, margin = 60.dp)
                        end.linkTo(parent.end, margin = 60.dp)
                        bottom.linkTo(parent.bottom, margin = 100.dp)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                country.callingCodes.forEachIndexed { _, codeString ->
                    CallingCodeCard(
                        numberString = "+$codeString",
                        onCallingCodeClicked = {
                            openDialerWithCallCode(codeString, context)
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCountryDetailScreen() {
    MaterialTheme {
        ScrollableColumn(
            onFavClicked = {},
            country = CountryModel(
                name = "Thailand",
                capital = "Bangkok",
                flag = "https://flagcdn.com/th.svg",
                region = "Central",
                subregion = "Central",
                languages = listOf(LanguageModel("Thai", "Thai")),
                callingCodes = listOf("66")
            )
        )
    }
}

fun openDialerWithCallCode(callCode: String, context: Context) {
    val data = "tel:+$callCode".toUri()
    val intent = Intent(Intent.ACTION_DIAL, data)
    try {
        context.startActivity(intent)
    } catch (s: SecurityException) {
        Toast.makeText(context, "Error occurred", Toast.LENGTH_LONG)
            .show()
    }
}
