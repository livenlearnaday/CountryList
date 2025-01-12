package io.github.livenlearnaday.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.livenlearnaday.presentation.R

@Composable
fun CommonAlertDialog(
    onClose: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    dialogText: String
) {
    AlertDialog(
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = onClose,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.dismiss))
            }
        }
    )
}