package io.github.livenlearnaday.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.livenlearnaday.presentation.R

@Composable
fun FavToggleButton(
    isFav: Boolean,
    onFavCLicked: () -> Unit,
    modifier: Modifier
) {
    Image(
        painter = if (isFav) painterResource(R.drawable.ic_star_filled) else painterResource(R.drawable.ic_star_outline),
        contentDescription = null,
        modifier = modifier
            .size(24.dp)
            .clickable(onClick = {
                onFavCLicked()
            })
    )
}
