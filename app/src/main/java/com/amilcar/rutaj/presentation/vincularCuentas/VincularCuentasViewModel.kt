package com.amilcar.rutaj.presentation.vincularCuentas

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.login.StoreUserData
import com.amilcar.rutaj.presentation.util.Variables
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils.trim
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class VincularCuentasViewModel @Inject constructor(
) : ViewModel() {


    val state : MutableState<VincularCuentasState> = mutableStateOf(VincularCuentasState())

    fun recuperarId(context :Context){
        val dataStore = StoreUserData(context)
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getProferences().collect { userdata ->
                state.value = state.value.copy(usuarioId = userdata.id.toString(),
                                                nombre = userdata.name)
                listarCuentas(context)
            }
        }
    }



    fun validarCuenta(cuenta : String, factura : String,context : Context) {

        val errorRegister = R.string.lista_vacia_vincular_cuentas

        if (cuenta.isBlank() || factura.isBlank()) {
            Toast.makeText(
                context,
                "Ingrese cuenta y número de facture",
                Toast.LENGTH_LONG
            ).show()
            return
        }


        state.value = state.value.copy(displayProgressBar = true)


        // verifico en la base de datos que la cuenta y la factura coincidan

        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/validar_cuenta.php"


        val stringRequest = @SuppressLint("ServiceCast")
        object : StringRequest(
            Method.POST,
            url,
            { response ->


                // si el mail y telefono estan registrados envio el mail

                if (trim(response.toString()) == "ok" ) {

                    state.value = state.value.copy(displayProgressBar = true)
                    state.value = state.value.copy(errorMessage = R.string.success_validate)

                    listarCuentas(context)


                } else if ((trim(response.toString()) == "existe" ) ) {
                    state.value = state.value.copy(errorMessage = R.string.cuentaCargada)
                }else {
                    errorRegister.let {
                        state.value = state.value.copy(errorMessage = it)
                    }
                }


            }, {
                errorRegister.let {
                    state.value = state.value.copy(errorMessage = it)
                }
            }) {

            // Press Ctr + O to find getParams
            override fun getParams() : MutableMap<String, String> {

                val hashMap = HashMap<String, String>()
                hashMap["usuario_id"] = state.value.usuarioId
                hashMap["conexion_id"] = cuenta
                hashMap["factura"] = factura

                return hashMap


            }
        }

        stringRequest.setShouldCache(false)
        requestQueue.add(stringRequest)



        state.value = state.value.copy(displayProgressBar = false, successValidate = true)

    }


    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }


    fun listarCuentas(context : Context) {

        state.value = state.value.copy(displayProgressBar = true)

        val lista = mutableListOf<String>()

        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/listar_cuentas.php"


        val stringRequest = @SuppressLint("ServiceCast")
        object : StringRequest(
            Method.POST,
            url,
            { response ->

                /*   val myClipboard =
                       context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                   myClipboard.setPrimaryClip(
                       ClipData.newPlainText(
                           "respuesta",
                           response.toString()
                       )
                   )*/


                if (response.isNotEmpty()) {

                    val jsonArray = JSONArray(response)

                    if (jsonArray.length() == 0) {
                        //lista vacia
                        state.value = state.value.copy(errorMessage = R.string.lista_vacia_vincular_cuentas)
                    } else {
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)

                            val conexionId = jsonObject.getString("conexion_id").toString()
                            val facturadoA = jsonObject.getString("facturado_a").toString()


                            lista += "$conexionId;$facturadoA"

                            if (lista.size>1){Variables.G_hasBeenConfiguratedBefore = true}
                        }

                        state.value = state.value.copy(listaConexiones = lista)
                    }


                    val ventana = if (state.value.listaConexiones.isEmpty()) {
                        1
                    } else {
                        kotlin.math.ceil(state.value.listaConexiones.size.toDouble() / 3)
                    }

                    state.value = state.value.copy(ventanas = ventana.toInt())
                    state.value = state.value.copy(successValidate = true)
                    state.value = state.value.copy(displayProgressBar = false)
                }
            },
            {
                state.value = state.value.copy(successValidate = false)
                state.value = state.value.copy(displayProgressBar = false)
                state.value = state.value.copy(errorMessage = R.string.lista_vacia_vincular_cuentas)
            }) {
            // Press Ctr + O to find getParams

            override fun getParams() : MutableMap<String, String> {

                val hashMap = HashMap<String, String>()
                hashMap["usuario_id"] = state.value.usuarioId

                return hashMap

            }
        }



        stringRequest.setShouldCache(false)
        requestQueue.add(stringRequest)

        state.value = state.value.copy(displayProgressBar = false, successValidate = true)

    }

}