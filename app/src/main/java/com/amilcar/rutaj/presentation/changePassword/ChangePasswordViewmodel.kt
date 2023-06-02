package com.amilcar.rutaj.presentation.changePassword

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.login.StoreUserData
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils.trim
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewmodel @Inject constructor(
) : ViewModel() {



    val state : MutableState<ChangePasswordState> = mutableStateOf(ChangePasswordState())


    fun changePassword(context : Context, password : String, newPassword : String) {


        // actualiza el password a la base
        // enviar el pass y el id del prof

        val errorMessage = if (password.isBlank() || newPassword.isBlank()) {
            R.string.error_input_empty
        } else if (password != newPassword) {
            R.string.error_incorrectly_repeated_password
        } else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = errorMessage)
            return
        }

        // actualizo el pass en el datastore
        actualizarPassword(context,newPassword)


        viewModelScope.launch(Dispatchers.IO) {
            state.value = state.value.copy(displayProgressBar = true)

            val messageError = R.string.error_change_password

            val requestQueue = Volley.newRequestQueue(context)

            val url = "https://rutaj.com.ar/rutajApp/android/update_password.php"

            val stringRequest = @SuppressLint("ServiceCast")
            object : StringRequest(
                Method.POST,
                url,
                { response ->

                    if (response.isNotEmpty() && trim(response.toString()) != "error") {

                        state.value = state.value.copy(displayProgressBar = false)
                        state.value = state.value.copy(successChange = true)
                        state.value = state.value.copy(password = newPassword)



                        //          Variables.G_usuarioPassword = password
                    } else {
                        state.value = state.value.copy(displayProgressBar = false)
                        state.value = state.value.copy(errorMessage = messageError)
                    }

                }, {
                    state.value = state.value.copy(displayProgressBar = false)
                    state.value = state.value.copy(errorMessage = messageError)
                }) {

                // Press Ctr + O to find getParams
                override fun getParams() : MutableMap<String, String> {

                    val hashMap = HashMap<String, String>()

                    hashMap["password"] = newPassword
                    hashMap["usuario_id"] = state.value.usuarioId

                    return hashMap

                }
            }

            stringRequest.setShouldCache(false)
            requestQueue.add(stringRequest)

        }
    }

 private fun actualizarPassword(context :Context, newPassword : String) {


        val dataStore = StoreUserData(context)
        viewModelScope.launch(Dispatchers.IO) {

            // obtengo valores y los paso state
            dataStore.getProferences().collect { userdata ->
                state.value = state.value.copy(
                    usuarioId = userdata.id.toString(),
                    email = userdata.email,
                    nombre = userdata.name,
                    telefono = userdata.telefono,
                    estado = userdata.state
                )
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            // actualizo el datastore
            dataStore.setPreferences(
                state.value.nombre,
                state.value.email,
                newPassword,
                state.value.telefono,
                state.value.estado,
                state.value.usuarioId.toInt()
            )

            state.value = state.value.copy(password = newPassword)
        }
}

fun hideErrorDialog() {
    state.value = state.value.copy(
        errorMessage = null
    )
}

}