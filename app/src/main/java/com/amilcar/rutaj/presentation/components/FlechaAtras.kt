package com.amilcar.rutaj.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun FlechaAtras(
    onBack: () -> Unit,
    text : String
)
{
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onBack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon",
                tint = MaterialTheme.colors.onSurface
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
    }
}


