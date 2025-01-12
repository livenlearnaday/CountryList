package io.github.livenlearnaday.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import io.github.livenlearnaday.presentation.R
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CustomImage(flagUrlString: String, modifier: Modifier) {

     KamelImage(
             modifier = modifier,
             resource = asyncPainterResource(
                 data = flagUrlString
             ),
             contentDescription = null,
             contentScale = ContentScale.Fit,
             onFailure = {
                 val fallbackPainter = painterResource(R.drawable.placeholder)
                 Image(fallbackPainter, contentDescription = "Flag image")
             },
             onLoading = {
                 CircularProgressIndicator()
             }
         )

}