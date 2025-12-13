package com.example.animehub.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.animehub.R

@Composable
fun ConfirmDeleteDialog(
    elementName: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onCancel() },
        title = { Text(stringResource(R.string.delete_element)) },
        text = { Text(stringResource(R.string.delete_element_confirmation, elementName)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.delete_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}