package com.amilcar.rutaj.presentation.vincularCuentas

import androidx.annotation.StringRes

data class VincularCuentasState(
    val usuarioId :String = "",
    val nombre :String = "",
    val displayProgressBar :Boolean = false,
    val successValidate:Boolean = false,
    val listaConexiones: List<String> = emptyList(),
    val ventanas: Int = 1,
    @StringRes val errorMessage : Int? = null
)