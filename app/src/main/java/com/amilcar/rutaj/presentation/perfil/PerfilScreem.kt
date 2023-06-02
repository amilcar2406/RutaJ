package com.amilcar.rutaj.presentation.perfil


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.FlechaAtras

@Composable
fun PerfilScreen(
    onBack : () -> Unit,
    onChangePassword : (String) -> Unit,
    viewModel : PerfilViewModel,
) {

    val context = LocalContext.current

    val state = viewModel.state.value

    LaunchedEffect(true ){ viewModel.leerDataStore(context)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        FlechaAtras(onBack = onBack, text = "Mi Perfil")


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)) {


            Column(
                modifier = Modifier
                    .width(80.dp)
                    .height(500.dp)
                    .padding(25.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "mail")
                Icon(imageVector = Icons.Filled.Person, contentDescription = "nombre")
                Icon(imageVector = Icons.Filled.Phone, contentDescription = "telefono")
                Icon(imageVector = Icons.Filled.Password, contentDescription = "password")
            }
            // fin de la primer columna

            // incio segunga columna
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(5.dp),
            ) {

                Text(text = "E-mail",
                    style = MaterialTheme.typography.body1)
                Text(
                    text = state.email,
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(text = "Nombre",
                    style = MaterialTheme.typography.body1)
                Text(
                    text = state.nombre,
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(text = "Teléfono de contacto",
                    style = MaterialTheme.typography.body1)
                Text(text = state.telefono,
                    style = MaterialTheme.typography.subtitle1)


                Spacer(modifier = Modifier.height(23.dp))

                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.body1
                    )

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = state.password,
                        modifier = Modifier.width(100.dp),
                        style = MaterialTheme.typography.subtitle1
                    )


                    Spacer(modifier = Modifier.width(30.dp))

                    ClickableText(

                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 13.sp
                                )
                            ) {
                                append("Modificar")
                            }
                        },
                        style = MaterialTheme.typography.body1
                    ) {
                        // abre vantena de cambio de passord
                        onChangePassword(state.usuarioId)
                    }
                }



            }
            // fin segunda columna
        } // fin de row
    }
}