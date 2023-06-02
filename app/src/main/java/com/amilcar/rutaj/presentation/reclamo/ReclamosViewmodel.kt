package com.amilcar.rutaj.presentation.reclamo

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ReclamosViewmodel @Inject constructor(
) : ViewModel() {


    fun llamarXReclamo(telefono :String,context :Context){
        val intent = Intent(
            Intent.ACTION_CALL,
            Uri.parse(telefono)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun enviarSmsWhatsApp(telefono :String,context :Context) {
        //val packageManager : PackageManager = packageManager
        //val url ="https://api.whatsapp.com/send?phone=" + telefono + "&text URLEncoder.encode(" + "" + ",enc = UTF-8)"
        val url = "https://api.whatsapp.com/send?phone=$telefono&text URLEncoder.encode(,enc = UTF-8)"


        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        intent.data = Uri.parse(url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}