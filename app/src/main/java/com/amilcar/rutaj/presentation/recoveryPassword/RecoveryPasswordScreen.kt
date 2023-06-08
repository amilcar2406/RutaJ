package com.amilcar.rutaj.presentation.login

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.presentation.components.EventDialog
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.components.RoundedButton
import com.amilcar.rutaj.presentation.components.TransparentTextField
import com.amilcar.rutaj.presentation.recoveryPassword.RecoveryPasswordState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RecoveryPasswordScreen(
    state : RecoveryPasswordState,
    email : String,
    onRecovery : (Array<String>, Context) -> Unit,
    onBack : () -> Unit,
    onDismissDialog : () -> Unit
) {

    val systemUiController = rememberSystemUiController()


    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.primary
    )

    val context = LocalContext.current
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = state.successRecovery){
        if (state.successRecovery) onBack()
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


            FlechaAtras(onBack = onBack, text = "Recupero de password")


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                Text(text = "Se enviará un mail a su casilla con el nuevo password.",
                style = MaterialTheme.typography.subtitle1)

                Text(email, modifier = Modifier.padding(top = 10.dp),
                        style = MaterialTheme.typography.subtitle2)

                TransparentTextField(
                    textFieldValue = phoneNumber,
                    textLabel = "Ingrese su número de teléfono",
                    keyboardType = KeyboardType.Number,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ),
                    imeAction = ImeAction.Next
                )

                Spacer(modifier = Modifier.height(15.dp))

                RoundedButton(
                    text = "Enviar",
                    displayProgressBar = state.displayProgressBar,
                    enabled = phoneNumber.value != "",
                    onClick = {
                        val mail = arrayOf(email,phoneNumber.value)

                        onRecovery(mail,context)

                    }
                )
            }


        }
    }


    if (state.errorMessage != null) {
        EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog, onBack = {})
    }

}
