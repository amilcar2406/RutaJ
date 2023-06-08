package com.amilcar.rutaj.presentation.cuentasVinculadas

import android.annotation.SuppressLint
import android.content.Context
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
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class CuentasVinculadasViewModel @Inject constructor(
) : ViewModel() {


    val state : MutableState<CuentasVinculadasState> = mutableStateOf(CuentasVinculadasState())




    fun recuperarId(context : Context){
        val dataStore = StoreUserData(context)
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getProferences().collect { userdata ->
                    state.value = state.value.copy(
                    usuarioId = userdata.id.toString(),
                    nombre = userdata.name)
                    listarCuentas(context)
            }
        }
    }


    private fun listarCuentas(context : Context){

        state.value = state.value.copy(displayProgressBar = true)

        val lista = mutableListOf<String>()

        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/listar_cuentas.php"

        viewModelScope.launch {
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
                            state.value =
                                state.value.copy(errorMessage = R.string.error_cuentas_vinculadas)
                        } else {
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)


                                val conexionId = jsonObject.getString("conexion_id").toString()
                                val facturadoA = jsonObject.getString("facturado_a").toString()


                                lista += "$conexionId;$facturadoA"


                                Variables.G_hasBeenConfiguratedBefore = true
                            }

                            state.value = state.value.copy(listaConexiones = lista)
                        }



                        state.value = state.value.copy(successList = true)
                        state.value = state.value.copy(displayProgressBar = false)
                    }
                },
                {
                    state.value = state.value.copy(successList = false)
                    state.value = state.value.copy(displayProgressBar = false)
                    state.value = state.value.copy(errorMessage = R.string.error_cuentas_vinculadas)
                }) {
                // Press Ctr + O to find getParams

                override fun getParams() : MutableMap<String, String> {

                    val hashMap = HashMap<String, String>()
                    hashMap["usuario_id"] =  state.value.usuarioId
                    return hashMap

                }
            }

            stringRequest.setShouldCache(false)
            requestQueue.add(stringRequest)

            state.value = state.value.copy(displayProgressBar = false, successList = true)
        }
    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }


}