package com.amilcar.rutaj.presentation.simuladorConsumo

data class SimuladorConsumoState (
    val cardHeladeraDesplegada : Boolean = false,
    val cardClimatizacionDesplegada : Boolean = false,
    val cardIluminacionDesplegada : Boolean = false,
    val cardLavadorasDesplegda : Boolean = false,
    val cardCocinasDesplegda : Boolean = false,
    val cardTermotanquesDesplegda : Boolean = false,
)
