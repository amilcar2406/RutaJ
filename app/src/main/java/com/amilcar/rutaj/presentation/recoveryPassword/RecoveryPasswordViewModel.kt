package com.amilcar.rutaj.presentation.recoveryPassword

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.sendMail.SendMail
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils.trim
import java.util.*
import javax.inject.Inject


/* Sending an email using kotlin and javax.mail

 Usage: java -jar app.jar <user> <password> <from> <to> <cc>
*/


@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor(
) : ViewModel() {
    val errorTelefono = R.string.error_telefono_registrado
    val errorMessage = R.string.error_recovery
    val state : MutableState<RecoveryPasswordState> = mutableStateOf(RecoveryPasswordState())




    fun validarMail(args : Array<String>,context :Context) {
        state.value = state.value.copy(displayProgressBar = true)
        val email = args[0]
        val phoneNumber = args[1]

        // verifico en la base de datos si el mail y el telefono coinciden


        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/forgot_password.php"


        val stringRequest = @SuppressLint("ServiceCast")
        object : StringRequest(
            Method.POST,
            url,
            { response ->

                // si el mail y telefono estan registrados envio el mail
                if (trim(response.toString()) != "error") {

                    val argsMail  = arrayOf(
                            email,
                            "Recupero de password Coop. Ruta J",
                            "Hemos recibido su solicitud de Recupero de Password.\r\n Su Nuevo Password es $response"
                    )

                    viewModelScope.launch(Dispatchers.Default) {
                        state.value = state.value.copy(displayProgressBar = true)

                        val sendMail = SendMail()

                        // Envio el mail
                        sendMail.SendMmailFunction(argsMail)

                        if (sendMail.stateMail) {
                            state.value = state.value.copy(successRecovery = true)
                        }

                        state.value = state.value.copy(displayProgressBar = false)
                    }


                } else {

                    state.value = state.value.copy(successRecovery = false)
                    state.value = state.value.copy(errorMessage = errorTelefono)
                }


            }, {
                errorMessage.let { state.value = state.value.copy(errorMessage = it) }

            }) {

            // Press Ctr + O to find getParams
            override fun getParams() : MutableMap<String, String> {

                val hashMap = HashMap<String, String>()
                hashMap["usuario_email"] = email
                hashMap["usuario_telefono"] = phoneNumber

                return hashMap

            }
        }

        stringRequest.setShouldCache(false)
        requestQueue.add(stringRequest)



        state.value = state.value.copy(displayProgressBar = false)

    }


    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }


}