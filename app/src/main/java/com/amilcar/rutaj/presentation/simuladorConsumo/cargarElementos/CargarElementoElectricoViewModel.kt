package com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.login.StoreUserData
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.model.Electrodomestico
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.model.Simulador
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class CargarElementoElectricoViewModel @Inject constructor(
) : ViewModel() {


    val state : MutableState<CargarElementoElectricoState> =
        mutableStateOf(CargarElementoElectricoState())


    init {
          state.value = state.value.copy(refreshListSimulador = true)
    }


     fun recuperarId(context : Context){
        val dataStore = StoreUserData(context)
        viewModelScope.launch(Dispatchers.IO) {
               dataStore.getProferences().collect { userdata ->
                   state.value = state.value.copy(usuarioId = userdata.id.toString())
                   listarSimulador(state.value.usuarioId,state.value.categoria,context)
               }
        }
    }




    fun cancelarValores(){
        state.value = state.value.copy(
            electrodomesticoValue = "",
            cantidadValue = "",
            horasDeUsoValue = "")
        mostrarCardAgregarElementos()
    }

    fun mostrarCardAgregarElementos() {
        state.value = state.value.copy(
            mostrarCardAgregarElemento = !state.value.mostrarCardAgregarElemento)
    }

    fun guardarElemento(context : Context) {
        val consumo = (state.value.cantidadValue.toDouble() * state.value.horasDeUsoValue.toDouble() * state.value.consumoHoraValue.toDouble() * 60)
        guardarElectrodomestico(consumo,context)
    }


    fun cambiarEstadoDeLista(tipoDato : String) {
        when (tipoDato) {
            "electrodomesticos" -> state.value = state.value.copy(
                mostrarListaDeElectrodomesticos = true,
                mostrarListaDeCantidades = false,
                mostrarListaDeHoras = false
            )
            "cantidad" -> state.value = state.value.copy(
                mostrarListaDeElectrodomesticos = false,
                mostrarListaDeCantidades = true,
                mostrarListaDeHoras = false
            )
            "horasDeUso" -> state.value = state.value.copy(
                mostrarListaDeElectrodomesticos = false,
                mostrarListaDeCantidades = false,
                mostrarListaDeHoras = true
            )
        }
        state.value = state.value.copy(mostrarPopUp = !state.value.mostrarPopUp)

    }


    fun almacenarValoresDeCampos(tipoDato : String, dato : String, consumoHora : String = "") {

        when (tipoDato) {
            "electrodomesticos" -> state.value =
                state.value.copy(electrodomesticoValue = dato, consumoHoraValue = consumoHora)
            "cantidades" -> state.value = state.value.copy(cantidadValue = dato)
            "horasDeUso" -> state.value = state.value.copy(horasDeUsoValue = dato)
        }

    }


    fun listarElectrodomesticos(categoria : String,context :Context) {


        state.value = state.value.copy(displayProgressBar = true)

        val lista = mutableListOf<Electrodomestico>()


        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/listar_electrodomesticos.php"


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
                                state.value.copy(errorMessage = R.string.error_electrodomesticos)
                        } else {
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)


                                val electId = jsonObject.getString("elect_id").toInt()
                                val electDetalle = jsonObject.getString("elect_detalle").toString()
                                val electConsumoHora =
                                    jsonObject.getString("elect_consumo_hora").toDouble()


                                val electro = Electrodomestico(
                                    electId,
                                    electDetalle,
                                    electConsumoHora
                                )

                                lista += electro

                            }

                            state.value = state.value.copy(listaElectodomesticos = lista)
                        }



                        state.value = state.value.copy(successList = true,
                                                        displayProgressBar = false)
                    }
                },
                {
                    state.value = state.value.copy(successList = false,
                                                   displayProgressBar = false,
                                                   errorMessage = R.string.error_electrodomesticos)
                }) {
                // Press Ctr + O to find getParams

                override fun getParams() : MutableMap<String, String> {

                    val hashMap = HashMap<String, String>()
                    hashMap["categoria"] = categoria


                    return hashMap

                }
            }



            stringRequest.setShouldCache(false)
            requestQueue.add(stringRequest)

            state.value = state.value.copy(displayProgressBar = false, successList = true)
        }
    }


    private fun guardarElectrodomestico(consumo : Double,context :Context) {


        state.value = state.value.copy(displayProgressBar = true)


        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/insertar_simulador.php"


        viewModelScope.launch {


            val stringRequest = @SuppressLint("ServiceCast")
            object : StringRequest(
                Method.POST,
                url,
                { response ->

                    if (response.isNotEmpty() && StringUtils.trim(response.toString()) != "error") {

                        state.value = state.value.copy(displayProgressBar = false,
                                                        successInsert = true,
                                                        refreshListSimulador = true,
                                                        electrodomesticoValue = "",
                                                        cantidadValue = "",
                                                        horasDeUsoValue = "")
                    } else {
                        state.value = state.value.copy(displayProgressBar = false,
                                                       errorMessage = R.string.error_insercion_simulador)
                    }

                }, {
                    state.value = state.value.copy(displayProgressBar = false,
                                            errorMessage = R.string.error_insercion_simulador)
                }) {

                // Press Ctr + O to find getParams
                override fun getParams() : MutableMap<String, String> {

                    val hashMap = java.util.HashMap<String, String>()

                    hashMap["detalle"] = state.value.electrodomesticoValue
                    hashMap["cantidad"] = state.value.cantidadValue
                    hashMap["horasuso"] = state.value.horasDeUsoValue
                    hashMap["consumodiario"] = state.value.consumoHoraValue
                    hashMap["consumo"] = consumo.toString()
                    hashMap["categoria"] = state.value.categoria
                    hashMap["usuario"] = state.value.usuarioId

                    return hashMap

                }
            }

            stringRequest.setShouldCache(false)
            requestQueue.add(stringRequest)

        }
    }

fun borrarElemento(id : String,context : Context){

    state.value = state.value.copy(displayProgressBar = true)


    viewModelScope.launch(Dispatchers.IO) {
        state.value = state.value.copy(displayProgressBar = true)

        val messageError = R.string.error_eleminacion_simulador

        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://rutaj.com.ar/rutajApp/android/eliminar_elemento_simulador.php"

        val stringRequest = @SuppressLint("ServiceCast")
        object : StringRequest(
            Method.POST,
            url,
            { response ->

                if (response.isNotEmpty() && StringUtils.trim(response.toString()) != "error") {

                    state.value = state.value.copy(displayProgressBar = false, refreshListSimulador =  true)

                }else{
                    state.value = state.value.copy(displayProgressBar = false,
                                                    refreshListSimulador =  true,
                                                    errorMessage = messageError )
                }

            }, {
                state.value = state.value.copy(displayProgressBar = false,
                                                refreshListSimulador =  true,
                                                errorMessage = messageError )
            }) {

            // Press Ctr + O to find getParams
            override fun getParams() : MutableMap<String, String> {

                val hashMap = java.util.HashMap<String, String>()

                hashMap["id"] = id

                return hashMap

            }
        }

        stringRequest.setShouldCache(false)
        requestQueue.add(stringRequest)



    }


  }


    private fun listarSimulador(usuarioId :String, categoria :String, context : Context) {


        state.value = state.value.copy(displayProgressBar = true)

        val lista = mutableListOf<Simulador>()

        state.value = state.value.copy( listaSimulador = lista,
                                    totalConsumoSimulador =  0.00)



        val requestQueue = Volley.newRequestQueue(context)
        var totalConsumo = 0.00

        val url = "https://rutaj.com.ar/rutajApp/android/listar_simulador.php"


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
                                state.value.copy(errorMessage = R.string.error_electrodomesticos)
                        } else {
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)


                                val id = jsonObject.getString("simulador_id").toString()
                                val detalle = jsonObject.getString("simulador_detalle").toString()
                                val cantidad = jsonObject.getString("simulador_cantidad").toInt()
                                val usoDiarioHoras = jsonObject.getString("simulador_hs_uso_diario").toDouble()
                                val consumoHora = jsonObject.getString("simulador_consumo_hora").toDouble()
                                val consumo = jsonObject.getString("simulador_consumo").toDouble()
                                val categ = jsonObject.getString("simulador_categoria")



                                val simulador = Simulador(
                                    id,
                                    detalle,
                                    cantidad,
                                    usoDiarioHoras,
                                    consumoHora,
                                    consumo,
                                    categ
                                )

                                lista += simulador
                                totalConsumo += consumo

                            }

                            state.value = state.value.copy(
                                listaSimulador = lista,
                                totalConsumoSimulador =  totalConsumo)
                        }

                        state.value = state.value.copy(successList = true,
                                                        displayProgressBar = false)
                    }
                },
                {
                    state.value = state.value.copy(successList = false,
                                                    displayProgressBar = false,
                                                    errorMessage = R.string.error_electrodomesticos)
                }) {
                // Press Ctr + O to find getParams


                override fun getParams() : MutableMap<String, String> {

                    val hashMap = java.util.HashMap<String, String>()



                    hashMap["categoria"] = categoria
                    hashMap["usuario"] = usuarioId

                    return hashMap

                }
            }


            stringRequest.setShouldCache(false)
            requestQueue.add(stringRequest)

            state.value = state.value.copy(displayProgressBar = false, successList = true,refreshListSimulador = false)
        }
    }




    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

}