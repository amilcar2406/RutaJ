package com.amilcar.rutaj.presentation.estados

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class EstadoCuentaViewModel @Inject constructor(
) : ViewModel() {


    val state : MutableState<EstadoCuentaState> = mutableStateOf(EstadoCuentaState())


    fun listarEstadoCuenta(context:Context, conexion_id : String) {

        state.value = state.value.copy(displayProgressBar = true)

        val lista = mutableListOf<String>()


        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/estado.php"

        viewModelScope.launch {
            val stringRequest = @SuppressLint("ServiceCast")
            object : StringRequest(
                Method.POST,
                url,
                { response ->


                    if (response.isNotEmpty()) {


                        val jsonArray = JSONArray(response)

                        if (jsonArray.length() == 0) {
                            //lista vacia
                            state.value =
                                state.value.copy(sinDeuda = true)
                        } else {
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)


                                val comprobante = jsonObject.getString("comprobante").toString()
                                val cuotaNro = jsonObject.getString("cuota_nro").toString()
                                val cuotaImporte = jsonObject.getString("cuota_importe").toString()
                                val cuotaRecImporte =
                                    jsonObject.getString("cuota_rec_importe").toString()
                                val cuota1Vto = jsonObject.getString("cuota_1_vto").toString()
                                val cuota2Vto = jsonObject.getString("cuota_2_vto").toString()


                                val item =
                                    "$comprobante;$cuotaNro;$cuotaImporte;$cuotaRecImporte;$cuota1Vto;$cuota2Vto"


                                lista += item

                            }

                            state.value = state.value.copy(listaEstado = lista)
                        }



                        state.value = state.value.copy(successList = true)
                        state.value = state.value.copy(displayProgressBar = false)
                    }
                },
                {
                    state.value = state.value.copy(successList = false)
                    state.value = state.value.copy(displayProgressBar = false)
                    state.value = state.value.copy(errorMessage = R.string.error_estado_cuenta)
                }) {
                // Press Ctr + O to find getParams

                override fun getParams() : MutableMap<String, String> {

                    val hashMap = HashMap<String, String>()
                    hashMap["conexion_id"] = conexion_id

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