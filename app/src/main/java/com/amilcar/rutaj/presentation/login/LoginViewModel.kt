package com.amilcar.rutaj.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.util.Variables
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils.trim
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {

    var dataStoreEnabled = mutableStateOf(true)
    val errorMessage = R.string.error_login
    private val auth: FirebaseAuth = Firebase.auth
    val state : MutableState<LoginState> = mutableStateOf(LoginState())


    fun login(
        email : String,
        password : String,
        nombre : String,
        context : Context
    ) {
        val dataStore = StoreUserData(context)



        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(
                context,
                "Ingrese Usuario y Password",
                Toast.LENGTH_LONG
            ).show()

        } else {


        // busca en el servidor

            val requestQueue = Volley.newRequestQueue(context)
            val url = "https://rutaj.com.ar/rutajApp/android/sign_in.php"

            viewModelScope.launch {
                state.value = state.value.copy(displayProgressBar = true)
                state.value = state.value.copy(
                    nombre = nombre,
                    email = email,
                    password = password)


                withContext((Dispatchers.IO)) {
                    val stringRequest = object : StringRequest(
                        Method.POST,
                        url,
                        { response ->

                            if (response.isNotEmpty() && trim(response.toString()) != "error") {

                                val jSonObject = JSONObject(response)
                                state.value = state.value.copy(
                                    usuarioId = jSonObject.getString("usuario_id").toInt()
                                )
                                state.value = state.value.copy(
                                    nombre = jSonObject.getString("usuario_nombre").toString()
                                )
                                state.value = state.value.copy(
                                    telefono = jSonObject.getString("usuario_telefono").toString()
                                )
                                state.value = state.value.copy(
                                    appHasBeenConfigurated = jSonObject.getString("usuario_app_configurada")
                                        .toBoolean()
                                )


                                // guardo shares preferences
                                viewModelScope.launch(Dispatchers.IO) {
                                if (dataStoreEnabled.value) {
                                    dataStore.setPreferences(
                                        state.value.nombre,
                                        state.value.email,
                                        state.value.password,
                                        state.value.telefono,
                                        true,
                                        state.value.usuarioId
                                    )

                                } else {
                                    dataStore.setPreferences("", "", "", "", false, 0)
                                }
                            }


                       //         Variables.G_usuarioId = state.value.id
                        //        Variables.G_usuarioNombre = state.value.nombre
                        //        Variables.G_usuarioTelefono = state.value.telefono
                                Variables.G_hasBeenConfiguratedBefore = state.value.appHasBeenConfigurated


                                state.value = state.value.copy(successLogin = true)
                                state.value = state.value.copy(displayProgressBar = false)


                            } else {
                                state.value = state.value.copy(successLogin = false)
                                state.value = state.value.copy(errorMessage = errorMessage)
                                state.value = state.value.copy(displayProgressBar = false)

                            }
                        },
                        {
                            state.value = state.value.copy(successLogin = false)
                            errorMessage.let { state.value = state.value.copy(errorMessage = it) }
                            state.value = state.value.copy(displayProgressBar = false)

                            /* Toast.makeText(
                                 context,
                                 "Error al intentar la conexión",
                                 Toast.LENGTH_LONG
                             ).show()**/
                        }) {

                        // Press Ctr + O to find getParams
                        override fun getParams() : MutableMap<String, String> {
                            val hashMap = HashMap<String, String>()
                            hashMap["usuario_email"] = email
                            hashMap["usuario_password"] = password
                            hashMap["usuario_nombre"] = nombre
                            return hashMap
                        }
                    }

                    stringRequest.setShouldCache(false)
                    requestQueue.add(stringRequest)
                }

            }


        }

    }



    fun signInWithGoogleCredential(
        credential : AuthCredential,loguinWithGoogle: () -> Unit )
    = viewModelScope.launch {

        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loguinWithGoogle()
                    }
                }
                .addOnFailureListener {
                }

        }catch (e:Exception) {

        }

    }





    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }


}


