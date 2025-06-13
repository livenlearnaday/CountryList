package io.github.livenlearnaday.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun CustomImage(
    flagUrlString: String,
    modifier: Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = flagUrlString,
        contentDescription = "Flag Image"
    )
}
