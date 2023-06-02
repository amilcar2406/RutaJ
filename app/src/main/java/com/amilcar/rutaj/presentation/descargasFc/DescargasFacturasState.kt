package com.amilcar.rutaj.presentation.descargasFc

import androidx.annotation.StringRes

data class DescargasFacturasState(
    val init : Boolean = true,
    val viewPdf : Boolean = false,
    val successList : Boolean = false,
    val displayProgressBar: Boolean = false,
    val listaFactura: List<String> = emptyList(),
    val listaUrl: List<String> = emptyList(),
    val terminosYCondiciones: Boolean = false,
    @StringRes val errorMessage : Int? = null)
