package com.amilcar.rutaj.presentation.login

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModelConRepo @Inject constructor(
    application : Application,
    private val repository : LoginRepository
) : ViewModel() {

    var dataStoreEnabled = mutableStateOf(true)
    val errorMessage = R.string.error_login
    val context = getApplication(application).applicationContext
    val dataStore = StoreUserData(context)

    val state : MutableState<LoginState> = mutableStateOf(LoginState())



    fun login1(
        usuario : String,
        password : String,
        nombre : String,
        estado : Boolean
    ) {


        // guardo el shared preference
        if (!estado) {
            dataStoreEnabled.value = false
        }



        if (usuario.isBlank() || password.isBlank()) {
            Toast.makeText(
                context,
                "Ingrese Usuario y Password",
                Toast.LENGTH_LONG
            ).show()

        } else {


            viewModelScope.launch {
                state.value = state.value.copy(displayProgressBar = true)

                // guardo shares preferences
                if (dataStoreEnabled.value) {
       //             dataStore.setPreferences(usuario, password, estado,0)
                } else {
         //           dataStore.setPreferences("", "",estado,0)
                }

                Log.d("mica entro al view","entro")

                val resultado = async(Dispatchers.IO) { repository.signIn(
                            usuario,
                            password,
                            nombre
                        )}

                    //resultado.await()


  /*                  .onSuccess { response ->

                            Log.d("mica despues de esperar", response.toString())

                            val jSonObject = JSONObject(response)
                            state.value =
                                state.value.copy(id = jSonObject.getString("usuario_id").toInt())
                            state.value = state.value.copy(
                                nombre = jSonObject.getString("usuario_nombre").toString()
                            )
                            state.value = state.value.copy(
                                appHasBeenConfigurated = jSonObject.getString("usuario_app_configurada")
                                    .toBoolean()
                            )

                            Variables.G_usuarioId = state.value.id
                            Variables.G_usuarioNombre = state.value.nombre
                            Variables.G_hasBeenConfiguratedBefore =
                                state.value.appHasBeenConfigurated


                            state.value = state.value.copy(successLogin = true)
                            state.value = state.value.copy(displayProgressBar = false)


                            if (nombre != "") {
                                // se loggeo con google, lazon el evento google
                                Variables.G_navigateWithGoogle = true
                            }


                        }.onFailure {
                            Log.d("mica viewmodel falla", "fallo")
                            state.value = state.value.copy(successLogin = false)
                            state.value = state.value.copy(errorMessage = errorMessage)
                            state.value = state.value.copy(displayProgressBar = false)

                        }

*/
            }

        }

    }


    fun loginWithGoogle(activity : Activity) {

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val client = GoogleSignIn.getClient(activity, gso)

        // lanza el selector de cuentas de google
        val signInIntent : Intent = client.signInIntent
        activity.startActivityForResult(signInIntent, 1)


    }

    fun finishLogin(task : Task<GoogleSignInAccount>) {
        try {
            val account : GoogleSignInAccount = task.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.

            login1(
                account.email.toString(),
                account.id.toString(),
                account.displayName.toString(),
                false
            )


        } catch (e : ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            state.value = state.value.copy(successLogin = false)
            errorMessage.let { state.value = state.value.copy(errorMessage = it) }

        }
    }


    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }


}


