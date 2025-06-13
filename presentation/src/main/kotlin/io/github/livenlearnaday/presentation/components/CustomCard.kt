package io.github.livenlearnaday.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import io.github.livenlearnaday.presentation.R
import io.github.livenlearnaday.presentation.ui.theme.darkGreen

@Composable
fun CallingCodeCard(
    numberString: String,
    onCallingCodeClicked: (numberString: String) -> Unit
) {
    Card(
        modifier = Modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ConstraintLayout {
                    // Create guideline from the start of the parent at 10% the width of the Composable
                    val startGuideline = createGuidelineFromStart(0.1f)
                    // Create guideline from the end of the parent at 10% the width of the Composable
                    val endGuideline = createGuidelineFromEnd(0.1f)
                    val (numberText, space, callBtn) = createRefs()

                    Text(
                        modifier = Modifier
                            .constrainAs(numberText) {
                                start.linkTo(startGuideline)
                                end.linkTo(space.start, margin = 10.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        text = numberString,
                        color = Color.Black
                    )

                    Spacer(
                        modifier = Modifier
                            .constrainAs(space) {
                                start.linkTo(numberText.end, margin = 60.dp)
                                end.linkTo(callBtn.start, margin = 60.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )

                    IconButton(
                        modifier = Modifier
                            .constrainAs(callBtn) {
                                start.linkTo(space.end, margin = 10.dp)
                                end.linkTo(endGuideline)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                        onClick = {
                            onCallingCodeClicked(numberString)
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_call),
                            contentDescription = null,
                            tint = darkGreen
                        )
                    }
                }
            }
        }
    }
}
