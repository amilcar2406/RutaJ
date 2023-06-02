package com.amilcar.rutaj.presentation.cuentasVinculadas

import androidx.annotation.StringRes

data class CuentasVinculadasState(
    val usuarioId :String = "",
    val nombre :String = "",
    val successList : Boolean = false,
    val displayProgressBar: Boolean = false,
    val listaConexiones: List<String> = emptyList(),
    @StringRes val errorMessage : Int? = null
)