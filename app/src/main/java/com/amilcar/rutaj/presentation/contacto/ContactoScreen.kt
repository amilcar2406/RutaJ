package com.amilcar.rutaj.presentation.contacto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.EventDialog
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.components.TransparentTextField

@Composable
fun ContactoScreen(
    tipoContacto : String,
    viewModel : ContactoViewModel,
    onContacto : (String, String,String, String) -> Unit,
    onBack : () -> Unit,
    onDismissDialog : () -> Unit
) {

    val state = viewModel.state.value
    val context = LocalContext.current

    val mensaje = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    var tituloFlechaAtras = "Realice su denuncia"
    var description = "Haga una breve descripción de su denuncia"
    var textLabel = "Ingrese su denuncia aquí"

    if (tipoContacto == "contacto") {
        tituloFlechaAtras = "Contáctenos"
    }
    if (tipoContacto == "contacto") {
        description = "Envíenos su comentario por favor"
    }
    if (tipoContacto == "contacto") {
        textLabel = "Deje su comentario aquí"
    }


    LaunchedEffect(true ){ viewModel.leerDataStore(context)}

    LaunchedEffect(key1 = state.successDenuncia) {
        if (state.successDenuncia) onBack()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),


            ) {


            FlechaAtras(
                onBack = onBack,
                text = tituloFlechaAtras,
            )




            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {


                Text(
                    text = description,
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(modifier = Modifier.height(15.dp))

                TransparentTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    textFieldValue = mensaje,
                    textLabel = textLabel,
                    keyboardType = KeyboardType.Ascii,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }
                    ),
                    imeAction = ImeAction.Done,
                    maxChar = 400

                )

                Spacer(modifier = Modifier.height(15.dp))



                if (!state.displayProgressBar) {
                    OutlinedButton(
                        modifier = Modifier
                            .width(180.dp)
                            .height(50.dp),
                        enabled = mensaje.value !="",
                        onClick = {
                            onContacto(
                                tipoContacto,
                                mensaje.value,
                                state.usuarioEmail,
                                state.usuarioId
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondaryVariant
                        ),
                        shape = RoundedCornerShape(50)
                    ) {

                        Text(
                            text = "Enviar",
                            fontStyle = FontStyle.Italic,
                            fontSize = 24.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }else{
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = MaterialTheme.colors.primaryVariant,
                        strokeWidth = 6.dp
                    )
                }


            }
        }
    }


    if (state.errorMessage != null)
        EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog, onBack = {})

}


