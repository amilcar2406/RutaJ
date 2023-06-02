package com.amilcar.rutaj

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.amilcar.rutaj.presentation.login.DatosUsuariosState
import com.amilcar.rutaj.presentation.login.StoreUserData
import com.amilcar.rutaj.presentation.navigation.Navigation
import com.amilcar.rutaj.ui.theme.RutaJTheme
import com.amilcar.rutaj.util.Variables
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var contador = 0

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        Variables.permisosOtorgados = isGranted
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        val state : MutableState<DatosUsuariosState> = mutableStateOf(DatosUsuariosState())

        setContent {
            RutaJTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    

                    
                    // obtengo datos del urusario del datastore

                    val dataStore = StoreUserData(this)
                    lifecycleScope.launch(Dispatchers.IO) {
                        dataStore.getProferences().collect { userdata ->

                            state.value = state.value.copy(
                                usuarioId =  userdata.id.toString(),
                                nombre = userdata.name,
                                email = userdata.email,
                                telefono = userdata.telefono,
                                password = userdata.pass
                            )

                            Variables.G_usuarioEmail = userdata.email
                            Variables.G_usuarioPassword = userdata.pass
                            Variables.G_usuarioState = userdata.state

                        }
                    }

                    requestTelefonPermission()


                     MainScreen()


                }
            }
        }
    }



    // cierre de aplicacion



    /*override fun onBackPressed() {

        if (contador == 0) {
            Toast.makeText(applicationContext, "Presione otra vez para salir", Toast.LENGTH_SHORT)
                .show()
            contador++
        } else {
            super.onBackPressed()
            if (contador > 1) finishAffinity()
        }

        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished : Long) {}

            override fun onFinish() {
                contador = 0
            }
        }
        timer.start()


    }*/


    private fun requestTelefonPermission() {
      when {
          ContextCompat.checkSelfPermission(
              this,
              android.Manifest.permission.CALL_PHONE
          ) == PackageManager.PERMISSION_GRANTED -> {
                 Variables.permisosOtorgados = true
          }

          ActivityCompat.shouldShowRequestPermissionRationale(
              this,
              android.Manifest.permission.CALL_PHONE
          ) -> Log.i("mica", "Show telefono permissions dialog")

          else -> requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
      }
  }




   /* // escucho la respuesta  del login de google
    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //val viewModel : LoginViewModelConRepo by viewModels()
        val viewModel : LoginViewModel by viewModels()

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.finishLogin(task)

        }
    }*/


    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    @Composable
    fun MainScreen() {


        //Navigation(this, mail, id, nombre)
        Navigation()



        //  no utlizada es para loggear directamten con google SIlentsignin
        fun verificarSesionGoogle(task : Task<GoogleSignInAccount>) : Array<String?> {

            val account : GoogleSignInAccount = task.getResult(ApiException::class.java)

            val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)

            var googleArray = arrayOfNulls<String>(3)

            googleSignInAccount?.let {
                googleArray =
                    arrayOf(it.email.toString(), it.id.toString(), it.displayName.toString())
            }

            return googleArray
        }

    }



}





