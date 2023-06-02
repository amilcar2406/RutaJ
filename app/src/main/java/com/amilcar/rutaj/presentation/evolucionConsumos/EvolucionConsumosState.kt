package com.amilcar.rutaj.presentation.evolucionConsumos

import androidx.annotation.StringRes

data class EvolucionConsumosState (
    val listaEvolucionConsumo : List<BarchartInput> = emptyList(),
    val successList : Boolean = false,
    val displayProgressBar : Boolean = false,
    @StringRes val errorMessage : Int? = null,

)