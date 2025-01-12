package io.github.livenlearnaday.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.livenlearnaday.presentation.R
import io.github.livenlearnaday.presentation.countrylist.CustomMenuItem
import io.github.livenlearnaday.presentation.ui.theme.CountryListTheme
import io.github.livenlearnaday.presentation.ui.theme.royalBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    isListScreen: Boolean = false,
    showSearchBar: Boolean = false,
    showAllFav: Boolean = false,
    onSearchIconClicked: () -> Unit,
    onSearchQuerySubmit: (query: String) -> Unit,
    onMoreIconClicked: () -> Unit,
    isMenuExpanded: Boolean = false,
    onMenuItemClicked: (menuItem: CustomMenuItem) -> Unit,
    onDismissMenu: () -> Unit,
    onBackPressed: () -> Unit
) {


    var text by remember { mutableStateOf("") }
    val searchBarExpanded by remember { mutableStateOf(false) }

    val windowInsets = WindowInsets.systemBars
    val innerPadding= windowInsets.asPaddingValues()

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            if (!showSearchBar) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = royalBlue),
        navigationIcon = {
            if (!isListScreen || showAllFav) Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                contentDescription = "Go Back",
                tint = Color.White,
                modifier = Modifier  //padding at the end of a component acts like margin
                    .padding(end = 20.dp)
                    .clickable {
                        onBackPressed()
                    },
            )
        },
        actions = {
            if (showSearchBar) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .height(64.dp),
                ) {
                    SearchBar(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 5.dp),
                        inputField = {
                            SearchBarDefaults.InputField(
                                query = text,
                                onQueryChange = {
                                    text = it
                                    onSearchQuerySubmit(it)
                                },
                                onSearch = { },
                                expanded = true,
                                onExpandedChange = { },
                                placeholder = {
                                    Text(text = "TypeSearch country's name or capital")
                                },
                                leadingIcon = {
                                    Icon(ImageVector.vectorResource(R.drawable.ic_close),
                                        contentDescription = null,
                                        modifier = Modifier.clickable {
                                            onBackPressed()
                                        })
                                },
                                trailingIcon = null,
                            )
                        },
                        expanded = searchBarExpanded,
                        onExpandedChange = {},
                    ) {}
                }
            } else {
                if (isListScreen) {
                    IconButton(
                        onClick = { onSearchIconClicked() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = { onMoreIconClicked() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_more_vert),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    CountryListDropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissMenu = {
                            onDismissMenu()
                        },
                        onSelect = {
                            onMenuItemClicked(it)
                        }
                    )
                }
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBar() {
    CountryListTheme {
        CustomTopAppBar(
            title = "CountryList",
            showSearchBar = false,
            onDismissMenu = {},
            onSearchIconClicked = {},
            onSearchQuerySubmit = {},
            onMenuItemClicked = {},
            onMoreIconClicked = {},
            isMenuExpanded = false,
            isListScreen = true,
            onBackPressed = {},
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBarWithSearchBar() {
    CountryListTheme {
        CustomTopAppBar(
            title = "CountryList",
            showSearchBar = true,
            onDismissMenu = {},
            onSearchIconClicked = {},
            onSearchQuerySubmit = {},
            onMenuItemClicked = {},
            onMoreIconClicked = {},
            isMenuExpanded = false,
            isListScreen = true,
            onBackPressed = {},
        )
    }
}