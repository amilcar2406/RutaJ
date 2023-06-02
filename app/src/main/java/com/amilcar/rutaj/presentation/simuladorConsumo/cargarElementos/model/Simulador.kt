package com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.model

data class Simulador (
    val simulador_id : String,
    val simulador_detalle :String,
    val simulador_cantidad :Int,
    val simulador_hs_uso_diario :Double,
    val simulador_consumo_hora :Double,
    val simulador_consumo : Double,
    val simulador_categoria: String,
)

