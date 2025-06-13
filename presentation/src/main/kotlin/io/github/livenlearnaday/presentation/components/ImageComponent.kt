package io.github.livenlearnaday.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.svg.SvgDecoder

@Composable
fun CustomImage(
    flagUrlString: String,
    modifier: Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .decoderFactory(SvgDecoder.Factory())
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .data(flagUrlString)
            .build(),
        contentDescription = "Flag Image"
    )
}
