package com.amilcar.rutaj.presentation.login

import android.util.Log
import com.android.volley.toolbox.StringRequest
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class LoginRepository @Inject constructor() {

  //  private val context = Contexts.getApplication(application).applicationContext

    fun signIn(
        usuario : String,
        password : String,
        nombre : String
    ) : String {

        var resultado=""

        Log.d("mica sigin", "entro al repo")
        Log.d("mica  antes del post ", "___________________________")
        // busca en el servidor


        Log.d("mica  antes del post ", "")


        try {
          //  val requestQueue = Volley.newRequestQueue(context)
            val url = "https://rutaj.com.ar/rutajApp/android/sign_in.php"
           // val stringRequest = @SuppressLint("ServiceCast")
            object : StringRequest(
                Method.POST,
                url,
                { response ->
                    Log.d("mica  entro en el post ", response.toString())
                    if (response.isNotEmpty() && StringUtils.trim(response.toString()) != "error") {
                        resultado = "ok"
                        Log.d("mica  entro en el repo y ", response.toString())
                    } else {
                        Log.d("mica no entro en el repo y", response.toString())
                        resultado = "error"
                    }

                }, {
                    Log.d("mica fallo enel repo i", it.toString())
                    resultado = "error"
                }) {
                // Press Ctr + O to find getParams

                // Press Ctr + O to find getParams
                override fun getParams() : MutableMap<String, String> {
                    val hashMap = HashMap<String, String>()
                    hashMap["usuario_email"] = usuario
                    hashMap["usuario_password"] = password
                    hashMap["usuario_nombre"] = nombre
                    return hashMap
                }

            }
        }catch (e: Exception){
            resultado = "error"
        }

        return resultado
    }
}


