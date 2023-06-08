package com.amilcar.rutaj.presentation.components


import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.util.Variables

@Composable
fun EventDialog(
    modifier: Modifier = Modifier,
    @StringRes errorMessage: Int,
    onDismiss: (() -> Unit)? = null,
    onBack:() -> Unit
) {
    AlertDialog(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .padding(16.dp),
        onDismissRequest = { onDismiss?.invoke()
                           if (errorMessage == R.string.success_validate)
                               Variables.G_hasBeenConfiguratedBefore = true
                               onBack()
                           },
        title = {
            Text(
                "Resultado",
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Text(
                text = LocalContext.current.getString(errorMessage),
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 16.sp
                )
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { onDismiss?.invoke()
                    if (errorMessage == R.string.success_validate) onBack()}) {
                    Text(text = "Aceptar", style = MaterialTheme.typography.button)
                }
            }
        }
    )
}