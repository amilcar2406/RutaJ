package com.amilcar.rutaj.presentation.cuentasVinculadas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.EventDialog
import com.amilcar.rutaj.presentation.components.FlechaAtras

@Composable
fun CuentasVinculadasScreen(
    title :String,
    subtitle : String,
    state : CuentasVinculadasState,
    onBack : () -> Unit,
    clickActivo : Boolean,
    onClickCuentas : (String) -> Unit,
    onDismissDialog : () -> Unit,
    viewModel : CuentasVinculadasViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(true){
        viewModel.recuperarId(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            FlechaAtras(onBack = onBack, text = title)


            Text(
                text = subtitle,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)


                  Text(modifier = Modifier
                      .fillMaxWidth()
                      .padding(end = 8.dp),
                      text = "Cuentas Vinculadas ${state.listaConexiones.size}",
                      fontSize = 14.sp,
                      textAlign = TextAlign.End)




                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {

                    items(state.listaConexiones.size) { item ->
                            MostrarCuentasVinculadas(
                            state.listaConexiones[item],
                            clickActivo,
                            onClick = {
                                onClickCuentas(state.listaConexiones[item])
                            }
                        )
                    }
                }


        }

        if (state.errorMessage != null) {
            EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
        }

    }
}

    @Composable
    fun MostrarCuentasVinculadas(
        cuenta : String,
        clickActivo :Boolean,
        onClick : () -> Unit
    ) {

        var nroCuenta =""
        var nombre =""
        val delimitador = ";"

        val cuentaNombre = cuenta.split(delimitador)


        if (cuentaNombre.isNotEmpty()){
            nroCuenta=cuentaNombre.first()
            nombre=cuentaNombre.last()
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    if (clickActivo) {
                        onClick()
                    }
                }
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 8.dp,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            border = BorderStroke(2.dp,MaterialTheme.colors.onPrimary)
        )
        {



            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = nroCuenta,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = nombre,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onPrimary
                )
            }

        }

    }

