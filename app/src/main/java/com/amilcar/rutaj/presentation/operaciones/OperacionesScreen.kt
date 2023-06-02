package com.amilcar.rutaj.presentation.operaciones

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.descargasFc.MostrarTitularCuenta


@Composable
fun OperacionesScreen(
    cuenta :String,
    onClickEstado : (String) -> Unit,
    onClickPagos : (String) -> Unit,
    onClickDescarga : (String) -> Unit,
    onClickConsumo : (String) -> Unit,
    onBack : () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {


        FlechaAtras(onBack = onBack, text = "Operaciones")

        MostrarTitularCuenta(cuenta = cuenta)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            verticalArrangement  = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick ={
                        onClickEstado(cuenta)},
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    border = BorderStroke(1.dp,MaterialTheme.colors.onSurface),
                    shape = MaterialTheme.shapes.small,
                ){
                    Image(
                        modifier = Modifier
                           .width(140.dp)
                           .height(110.dp),
                        painter = painterResource(id = R.drawable.estado),
                        contentDescription = "estado",
                        contentScale = ContentScale.Crop
                    )
                }
                TextButton(
                    onClick ={onClickPagos(cuenta)},
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    border = BorderStroke(1.dp,MaterialTheme.colors.onSurface),
                    shape = MaterialTheme.shapes.small,
                ){
                    Image(
                        modifier = Modifier
                            .width(140.dp)
                            .height(110.dp),
                        painter = painterResource(id = R.drawable.pagos),
                        contentDescription = "pagos",
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick ={onClickDescarga(cuenta)},
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    border = BorderStroke(1.dp,MaterialTheme.colors.onSurface),
                    shape = MaterialTheme.shapes.small,
                ){
                    Image(
                        modifier = Modifier
                            .width(140.dp)
                            .height(110.dp),
                        painter = painterResource(id = R.drawable.descarga),
                        contentDescription = "descarga",
                        contentScale = ContentScale.Crop
                    )
                }
                TextButton(
                    onClick ={onClickConsumo(cuenta)},
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    border = BorderStroke(1.dp,MaterialTheme.colors.onSurface),
                    shape = MaterialTheme.shapes.small,
                ){
                    Image(
                        modifier = Modifier
                            .width(140.dp)
                            .height(110.dp),
                        painter = painterResource(id = R.drawable.consumo),
                        contentDescription = "evolucion consumo",
                        contentScale = ContentScale.Crop
                    )
                }
            }

             Spacer(modifier = Modifier.width(5.dp))

            Text(text = "Seleccione una opción para operar",
                 style = MaterialTheme.typography.subtitle1,
                 fontWeight = FontWeight.Light,
                 fontStyle = FontStyle.Italic
            )
        }

    }
}

