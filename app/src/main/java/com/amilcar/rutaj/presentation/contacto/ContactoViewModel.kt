package com.amilcar.rutaj.presentation.contacto

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.login.StoreUserData
import com.amilcar.rutaj.presentation.sendMail.SendMail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactoViewModel @Inject constructor(
) : ViewModel() {



    val state : MutableState<ContactoState> = mutableStateOf(ContactoState())


    fun leerDataStore(context : Context){
        val dataStore = StoreUserData(context)
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getProferences().collect { userdata ->
                state.value = state.value.copy(
                    usuarioId = userdata.id.toString(),
                    usuarioEmail = userdata.email,
                )
            }
        }
    }


    fun contacto(
        tipoContacto : String,
        mensaje : String,
        usuarioEmail : String,
        usuarioId : String
    ) {

         val errorDenuncia = R.string.error_denuncia

        viewModelScope.launch(Dispatchers.Default) {
            state.value = state.value.copy(displayProgressBar = true)

            val emailDestino = "gerencia@rutaj.com.ar"  // mail de destino

            val subject = if (tipoContacto == "contacto") {
                "Mensaje desde la app"
            } else {
                "Denuncia por Hurto"
            }

            val message = "$mensaje\n\r $usuarioEmail $usuarioId"


            val args = arrayOf(emailDestino, subject, message)


            val sendMail = SendMail()


            // Envio el mail
            sendMail.sendEmailFunction(args)
            if (sendMail.stateMail) {
                state.value = state.value.copy(successDenuncia = true)
                state.value = state.value.copy(displayProgressBar = false)
            } else {
                state.value = state.value.copy(displayProgressBar = false)
                state.value = state.value.copy(errorMessage = errorDenuncia)
            }
        }

    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

}