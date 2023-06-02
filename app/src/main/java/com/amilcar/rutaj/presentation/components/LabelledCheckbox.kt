package com.amilcar.rutaj.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LabelledCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            //modifier = Modifier.background(MaterialTheme.colors.secondaryVariant),
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            //colors = colors,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.secondaryVariant,
                checkmarkColor = MaterialTheme.colors.onSecondary
            ),
        )
        Spacer(Modifier.width(15.dp))

        Text(label,
             color = MaterialTheme.colors.secondaryVariant,
             style = MaterialTheme.typography.body2,
             fontWeight = FontWeight.Medium,
             modifier = Modifier.width(150.dp)
        )
    }
}