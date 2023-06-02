package com.amilcar.rutaj.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Dialog(
     titulo: String,
     leyenda: String,
     leyenda1: String,
     showDialog: Boolean,
     confirmDialog: () -> Unit,
     dismissDialog: () -> Unit
) {
    if (showDialog){
      AlertDialog(onDismissRequest = {},
      backgroundColor = Color.LightGray,
      shape = MaterialTheme.shapes.medium,
      title = { Text(
          text = titulo,
          style = TextStyle(fontSize = 22.sp,
          fontWeight = FontWeight.Bold)
      )},
       text={
           Column() {
               Text(leyenda)  //  puede ser no solo texto
               Spacer(modifier = Modifier.height(8.dp))
               Text(leyenda1)  //  puede ser no solo texto
           }

       },
          confirmButton = {
                Button(onClick = {
                    confirmDialog()
                }) {
                    Text(text = "Aceptar")
                }
          },

          dismissButton = {
              Button(onClick = { dismissDialog() }) {
                  Text(text = "Cancelar")
              }
          }
      )
    }

}