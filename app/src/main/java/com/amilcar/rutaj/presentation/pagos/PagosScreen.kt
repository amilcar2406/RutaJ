package com.amilcar.rutaj.presentation.pagos

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.descargasFc.MostrarTitularCuenta

@Composable
fun PagosScreen(
    cuenta : String,
    title : String,
    onBack : () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    )
    {

        val url = "https://pum.multipago.com.ar"
        val context = LocalContext.current

        FlechaAtras(onBack = onBack, text = title)

        MostrarTitularCuenta(cuenta = cuenta)

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier
                .padding(15.dp)
                .height(300.dp)
                .fillMaxWidth()
        ) {
            item() {

                Text(
                    text = "Podés pagar por medio de Transferencia Bancaria",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )

                Row(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "Nuestro CBU es :  ",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "coop.rutaj",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                }



                Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Sino podés pagar sin factura",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onBackground
                )

                Text(
                    text = "a traves de PUM Multipago en 5 pasos",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onBackground
                )

                Spacer(Modifier.height(5.dp))

                Row(modifier = Modifier.fillMaxWidth()) {

                    Icon(imageVector = Icons.Filled.Done, contentDescription = "tilde")

                    Spacer(Modifier.width(20.dp))

                    Text(
                        text = "Hace CLICK en la Imagen de abajo",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Spacer(Modifier.height(5.dp))

                Text(
                    text = "1 - Seleccioná SIN FACTURA",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onBackground
                )

                Text(
                    text = "2 - Seleccioná entidad Ruta J",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onBackground
                )

                Text(
                    text = "3 - Ingresá tu ID de usuario ",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    text = "4 - Seleccioná la factura a Pagar",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    text = "5 - Seguí los pasos...",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onBackground
                )

            }
        }



        Image(
            painter = painterResource(id = R.drawable.multipago),
            contentDescription = "multipago",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clickable {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    )
                    context.startActivity(urlIntent)
                }
        )

    }
}