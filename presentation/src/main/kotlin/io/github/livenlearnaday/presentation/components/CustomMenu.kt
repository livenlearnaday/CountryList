package io.github.livenlearnaday.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.livenlearnaday.presentation.countrylist.CustomMenuItem
import io.github.livenlearnaday.presentation.countrylist.CustomMenuItem.Companion.customMenuItems
import io.github.livenlearnaday.presentation.ui.theme.lightGrey
import timber.log.Timber

@Composable
fun CountryListDropdownMenu(
    expanded: Boolean,
    items: List<CustomMenuItem> = customMenuItems,
    onSelect: (itemSelected: CustomMenuItem) -> Unit,
    onDismissMenu: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(
                with(LocalDensity.current) {
                    CustomMenuItem.ClearAllFavs.title.length.toDp()
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    onDismissMenu()
                }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.title) },
                        modifier = Modifier.background(color = lightGrey),
                        onClick = {
                            onSelect(item)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryListDropdownMenu() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .padding(bottom = 2.dp, top = 2.dp)
                .width(
                    with(LocalDensity.current) {
                        Timber.d("log  CustomMenuItem.longestText: ${CustomMenuItem.longestText}")
                        CustomMenuItem.longestText.length.toDp()
                    }
                ),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Box {
                DropdownMenu(
                    expanded = true,
                    onDismissRequest = {}
                ) {
                    customMenuItems.forEach { customMenuItem ->
                        DropdownMenuItem(
                            text = { Text(text = customMenuItem.title) },
                            modifier = Modifier.background(color = lightGrey),
                            onClick = { }
                        )
                    }
                }
            }
        }
    }
}
