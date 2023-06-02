package com.amilcar.rutaj.presentation.registration

import android.annotation.SuppressLint
import android.app.Application
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.internal.Contexts
import java.util.*
import javax.inject.Inject

class RegisterRepository @Inject constructor(application : Application){

    private val context = Contexts.getApplication(application).applicationContext

   fun singUp(
       name: String,
       email: String,
       phoneNumber: String,
       password: String,
   ):Result<String> {

       var result = false
       var respuesta = ""

       val requestQueue = Volley.newRequestQueue(context)

       val url = "https://rutaj.com.ar/rutajApp/android/sign_up.php"

       val stringRequest = @SuppressLint("ServiceCast")
       object : StringRequest(
           Method.POST,
           url,
           { response ->
               result = true
               respuesta = response

           },{error ->
               result = false
               respuesta = error.toString()

           }) {
           // Press Ctr + O to find getParams

           override fun getParams() : MutableMap<String, String> {

               val hashMap = HashMap<String, String>()


               hashMap["usuario_nombre"] = name
               hashMap["usuario_email"] = email
               hashMap["usuario_password"] = password
               hashMap["usuario_telefono"] = phoneNumber

               return hashMap

           }
       }

       stringRequest.setShouldCache(false)
       requestQueue.add(stringRequest)

       return if (result) {
           Result.success(respuesta)
       }else{
           Result.success(respuesta)
       }


   }


}