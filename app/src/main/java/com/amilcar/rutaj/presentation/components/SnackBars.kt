package com.amilcar.rutaj.presentation.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.ui.theme.Green800
import kotlinx.coroutines.launch


@Composable
fun SnackBars(cartel: String,displaySnackBar: Boolean) {
    if (displaySnackBar){
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
       Scaffold(
           scaffoldState = scaffoldState,
           snackbarHost = {
               // reuse default SnackbarHost to have default animation and timing handling
               SnackbarHost(it) { data ->
                   Snackbar(
                       snackbarData = data,
                       backgroundColor = Green800,
                       contentColor = Color.White,
                       elevation = 16.dp
                   )
               }
           }
           )

       {


           scope.launch {
                   scaffoldState.snackbarHostState.showSnackbar(
                   message = cartel,
                   duration = SnackbarDuration.Short)
           }

       }

       /* Text(text = "Snackbars", style = typography.h6, modifier = Modifier.padding(8.dp))
        // Normal Snackbar
        Snackbar(modifier = Modifier
            .padding(4.dp)
            .background(Green800)
            ) {
            Text(text = cartel)
        }*/

   /*
        // Snackbar with action item
        Snackbar(
            modifier = Modifier.padding(4.dp),
            action = {
                TextButton(onClick = {}) {
                    Text(text = "Remove")
                }
            }
        ) {
            Text(text = cartel)
        }

        // Snackbar with action item below text
        Snackbar(
            modifier = Modifier.padding(4.dp),
            actionOnNewLine = true,
            action = {
                TextButton(onClick = {}) {
                    Text(text = "Remove")
                }
            }
        ) {
            Text(text = cartel)
        }*/
    }

}