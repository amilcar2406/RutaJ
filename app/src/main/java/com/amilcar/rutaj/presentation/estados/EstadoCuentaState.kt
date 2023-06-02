package com.amilcar.rutaj.presentation.estados

import androidx.annotation.StringRes

data class EstadoCuentaState(
 val successList :Boolean = false,
 val displayProgressBar : Boolean = false,
 val listaEstado :List<String> = emptyList(),
 val sinDeuda : Boolean = false,
 @StringRes val errorMessage : Int? = null
)