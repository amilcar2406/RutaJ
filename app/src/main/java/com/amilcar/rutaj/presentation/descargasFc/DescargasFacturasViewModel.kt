package com.amilcar.rutaj.presentation.descargasFc

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.util.downloadManager.AndroidDownloader
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject


@HiltViewModel
class DescargasFacturasViewModel @Inject constructor(
) : ViewModel() {


    val state : MutableState<DescargasFacturasState> = mutableStateOf(DescargasFacturasState())


    fun terminosYCondiciones(){
        state.value = state.value.copy(terminosYCondiciones = !state.value.terminosYCondiciones)
    }


    fun listarFacturas(context :Context, cuenta : String) {


        val delimitador = ";"
        val cuentaNombre = cuenta.split(delimitador)
        val nroCuenta = cuentaNombre.first()



        state.value = state.value.copy(displayProgressBar = true)


        viewModelScope.launch(Dispatchers.IO) {

            val lista = mutableListOf<String>()
            val listaUrl = mutableListOf<String>()

            //withContext(Dispatchers.IO){repository.ListaFacturasDelServidor(nroCuenta)}
//
            val requestQueue = Volley.newRequestQueue(context)

            val url = "https://rutaj.com.ar/rutajApp/android/listar_facturas.php"


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
                            // exitoso pero vacio
                            state.value =
                                state.value.copy(errorMessage = R.string.lista_facturas_vacias)
                        } else {

                            for (i in 0 until jsonArray.length()) {
                                val arrayItem = jsonArray[i].toString()


                                val factura = arrayItem.substring(arrayItem.length - 10)


                                lista += factura
                                listaUrl += arrayItem

                            }
                            // exitoso
                            state.value = state.value.copy(listaFactura = lista, listaUrl = listaUrl)
                            state.value = state.value.copy(successList = true)
                            state.value = state.value.copy(displayProgressBar = false)
                        }
                    }
                },
                {
                    // errpr
                    state.value = state.value.copy(successList = false)
                    state.value = state.value.copy(displayProgressBar = false)
                    state.value = state.value.copy(errorMessage = R.string.error_listar_facturas)
                }) {
                // Press Ctr + O to find getParams

                override fun getParams() : MutableMap<String, String> {

                    val hashMap = HashMap<String, String>()
                    hashMap["cuenta"] = nroCuenta

                    return hashMap
                }

            }

            stringRequest.setShouldCache(false)
            requestQueue.add(stringRequest)

        }

    }
    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

    fun downloadFacturas(nroCuenta : String,nroFactura : String, listaUrl : List<String>, context : Context) {


        for (i in 0 until listaUrl.size - 1){
            //val url = listaUrl.get(i)
            val url = listaUrl[i]
            val position = url.indexOf(nroFactura)
            if (position>0){
                // descarga la factura tomando el item q es el path completo
                descargaFC(context, nroFactura, nroCuenta)
                break
            }
        }

    }

    fun descargaFC(context: Context,  nombreArchivo:String, nroCuenta :String){
        val downloader = AndroidDownloader(context)
        val urlOfTheFile = "https://rutaj.com.ar/fcsToDownloads/$nroCuenta/$nroCuenta-$nombreArchivo"
        downloader.DonwloadFile(urlOfTheFile, "$nroCuenta-$nombreArchivo")


    }



}