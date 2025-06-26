package io.github.livenlearnaday.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.livenlearnaday.presentation.R
import io.github.livenlearnaday.presentation.countrylist.CustomMenuItem
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
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            if (showSearchBar) {
                AppBarTextField(
                    onValueChange = {
                        onSearchQuerySubmit(it)
                    },
                    hint = "country's name or capital",
                    containerColor = royalBlue,
                    contentColor = Color.White
                )
            } else {
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
            if (!isListScreen || showAllFav || showSearchBar) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                    contentDescription = "Go Back",
                    tint = Color.White,
                    modifier = Modifier //padding at the end of a component acts like margin
                        .padding(end = 20.dp)
                        .clickable {
                            onBackPressed()
                        }
                )
            }
        },
        actions = {
            if (isListScreen) {
                if (!showSearchBar) {
                    IconButton(
                        onClick = { onSearchIconClicked() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
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
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBar() {
    MaterialTheme {
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
            onBackPressed = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBarWithSearchBar() {
    MaterialTheme {
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
            onBackPressed = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    hint: String,
    containerColor: Color = Color.White,
    contentColor: Color = Color.Black,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val interactionSource = remember { MutableInteractionSource() }

    val containerColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        focusedTextColor = contentColor,
        unfocusedTextColor = contentColor,
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        disabledContainerColor = containerColor,
        cursorColor = contentColor
    )

    var text by rememberSaveable { mutableStateOf("") }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier =
        modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        value = text,
        onValueChange = {
            text = it
            onValueChange(text.replace("\n", ""))
        },
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        placeholder = { Text(text = hint, color = contentColor.copy(alpha = 0.3f), fontSize = 12.sp) },
        trailingIcon = {
            if (isFocused) {
                Box(
                    modifier = modifier.padding(end = 10.dp)
                ) {
                    Icon(
                        modifier = modifier
                            .align(Alignment.CenterEnd)
                            .clickable {
                                text = ""
                                onValueChange("")
                            },
                        imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                        contentDescription = "Clear Text",
                        tint = contentColor
                    )
                }
            }
        },
        colors = containerColors
    )
}
