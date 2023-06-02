package com.amilcar.rutaj.presentation.reclamo

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Sms
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.presentation.components.FlechaAtras

@Composable
fun ReclamoScreen(
    onBack: () -> Unit,
    onLlamarAOficina :(String,Context) -> Unit,
    onLlamarAGuardia :(String, Context) -> Unit,
    onSendMensaje :(String, Context) -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    )
    {

        FlechaAtras(onBack = onBack, text = "Comunicate con nosotros")

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .fillMaxSize()
            ){

            Text(text = "Si tenes un reclamo o duda comunicate con nosotros. " +
                         "En horario de oficina de Lunes a Viernes de 07 a 14 hs" +
                        " llamá a la oficina, fuera de esos horarios llamá a la guardia ",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Column(
                    Modifier
                        .width(35.dp)
                        .padding(start = 15.dp, top = 15.dp)
                ) {
                    // icono de fila
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "telefono",
                        tint = MaterialTheme.colors.onSurface
                    )
                }


                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    // Subtítulo
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "De Lunes a Viernes de 7 a 14hs",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                    // Encabezado
                    Text(
                        text = "Llama a la oficina", style = MaterialTheme.typography.h6,
                        modifier = Modifier.clickable { onLlamarAOficina("tel:3442492055",context)},
                        color = MaterialTheme.colors.onSurface
                    )

                }

            }

            Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Column(
                    Modifier
                        .width(35.dp)
                        .padding(start = 15.dp, top = 15.dp)
                ) {
                    // icono de fila
                    Icon(
                        imageVector = Icons.Filled.PhoneAndroid,
                        contentDescription = "movil",
                        tint = MaterialTheme.colors.onSurface
                    )
                }


                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    // Subtítulo
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "De tarde, sabados , domingos o feriados.",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                    // Encabezado
                    Text(
                        text = "Llama a a la guardia", style = MaterialTheme.typography.h6,
                        modifier = Modifier.clickable { onLlamarAGuardia("tel:3442640820",context)},
                        color = MaterialTheme.colors.onSurface
                    )

                }
            }
            Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)


            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Column(
                    Modifier
                        .width(35.dp)
                        .padding(start = 15.dp, top = 15.dp)
                ) {
                    // icono de fila
                    Icon(
                        imageVector = Icons.Filled.Sms,
                        contentDescription = "movil",
                        tint = MaterialTheme.colors.onSurface
                    )
                }


                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    // Subtítulo
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "Por cuestriones administrativas podes usar WhatsApp",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                    // Encabezado
                    Text(
                        text = "Envía un mensaje al 3442-640781", style = MaterialTheme.typography.h6,
                        modifier = Modifier.clickable { onSendMensaje("3442640781",context) },
                        color = MaterialTheme.colors.onSurface
                    )

                }
            }
            Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)



        }



    }
}