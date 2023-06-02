package com.amilcar.rutaj.presentation.evolucionConsumos

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
class EvolucionConsumosViewModel @Inject constructor() : ViewModel() {


    val state : MutableState<EvolucionConsumosState> = mutableStateOf(EvolucionConsumosState())


    fun evolucionConsumos(context : Context, conexion_id : String) {

        state.value = state.value.copy(displayProgressBar = true)

        val listaEvolucion = mutableListOf<BarchartInput>()


        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/listar_consumos.php"

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
                                state.value.copy(errorMessage = R.string.error_evolucion_consumos)
                        } else {
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)


                                val consumoKwh = jsonObject.getString("consumo_kwh").toString()
                                val consumoPeriodo =
                                    jsonObject.getString("consumo_periodo").toString()


                                val item = BarchartInput(
                                    consumo = consumoKwh.toInt(),
                                    valorBarra = 0,
                                    description = consumoPeriodo
                                )

                                listaEvolucion += item

                            }

                            state.value =
                                state.value.copy(listaEvolucionConsumo = valorBarra(listaEvolucion))
                        }



                        state.value = state.value.copy(successList = true)
                        state.value = state.value.copy(displayProgressBar = false)
                    }
                },
                {
                    state.value = state.value.copy(successList = false)
                    state.value = state.value.copy(displayProgressBar = false)
                    state.value = state.value.copy(errorMessage = R.string.error_evolucion_consumos)
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


    fun valorBarra(
        lista : List<BarchartInput>
    ) : List<BarchartInput> {

        // si el mayor es > que 250  hago este proceso sino valorBarrra = consumo
        // divido a todos por 250 obntego N para cada uno
        //  divido 250 por el N del numero mas grande obnteno B
        // multiplico N x B para obtener el valor barra


        val listaNueva = mutableListOf<BarchartInput>()
        val listaConsumos = mutableListOf<Int>()
        val sizeLista = lista.size


        for (i in 0 until sizeLista) {
            listaConsumos.add(lista[i].consumo)
        }


        val maximo = listaConsumos.maxOrNull()?.toInt()
        val listaValorDivision = mutableListOf<Float>()


        // divido todos los valores de la lista por 250 que es el tope del grafico
        if (maximo != null) {
            if (maximo > 250) {


                var valorDivisionMaximo = 0.0f


                for (i in 0 until sizeLista ) {
                    listaValorDivision.add((listaConsumos[i] / 250.00).toFloat())

                    if ((listaConsumos[i] / 250.00).toFloat() > valorDivisionMaximo) {
                        valorDivisionMaximo = (listaConsumos[i] / 250.00).toFloat()
                    }

                }


                val multiplicador = ((250.00 / valorDivisionMaximo).toFloat())



                var periodo =""


                for (i in 0..6 ) {

                    if (i == sizeLista - 1){
                        periodo = lista[i].description // tomo el ultimo periodo con consumos
                    }

                    if (i > sizeLista - 1 ){
                        // agrgo la descripcion calculando los meses para atas
                        val mes  = periodo.substring(0,2)
                        val anio  = periodo.substring(3,5)


                        periodo = if (mes.toInt() -1 == 0){
                            "06/"+(anio.toInt()-1).toString()
                        }else{
                            "0"+(mes.toInt() -1).toString()+"/" +anio
                        }

                        val item = BarchartInput(
                            consumo = 0,
                            description = periodo,
                            valorBarra = 0
                        )
                        listaNueva += item
                    }else{
                        val valorBarra = listaValorDivision[i] * multiplicador.toInt()
                        val item = BarchartInput(
                            consumo = lista[i].consumo,
                            description = lista[i].description,
                            valorBarra = valorBarra.toInt()
                        )
                        listaNueva += item
                    }

                }

            }
        }


        return listaNueva
    }


}

