package io.github.livenlearnaday.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import io.github.livenlearnaday.presentation.R
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.takeFrom
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.Default
import io.kamel.image.config.LocalKamelConfig
import io.kamel.image.config.resourcesFetcher

@Composable
fun CustomImage(flagUrlString: String, modifier: Modifier) {
    val context = LocalContext.current

    val androidConfig = KamelConfig {
        takeFrom(KamelConfig.Default)
        resourcesFetcher(context)
        svgCacheSize = 200

    }

    CompositionLocalProvider(LocalKamelConfig provides androidConfig) {
        KamelImage(
            resource = {
                asyncPainterResource(data = flagUrlString)
            },
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Fit,
            onFailure = {
                val fallbackPainter = painterResource(R.drawable.placeholder)
                Image(fallbackPainter, contentDescription = "Flag image")
            },
            onLoading = {
                DotPulsingLoadingIndicator()
            }
        )
    }

}