package com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos

import androidx.annotation.StringRes
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.model.Electrodomestico
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.model.Simulador

data class CargarElementoElectricoState(
    val usuarioId : String = "",
    val categoria: String ="",
    val listaElectodomesticos : List<Electrodomestico> = emptyList(),
    val listaSimulador : List<Simulador> = emptyList(),
    val displayProgressBar : Boolean = false,
    val refreshListSimulador:Boolean = false,
    val successList : Boolean = false,
    val successInsert : Boolean = false,
    val mostrarCardAgregarElemento : Boolean = false,
    val mostrarListaDeElectrodomesticos:Boolean = false,
    val mostrarListaDeCantidades:Boolean = false,
    val mostrarPopUp:Boolean = false,
    val mostrarListaDeHoras:Boolean = false,
    val electrodomesticoValue : String = "",
    val consumoHoraValue : String = "",
    val cantidadValue : String = "",
    val horasDeUsoValue : String = "",
    val totalConsumoSimulador : Double = 0.00,
    @StringRes val errorMessage : Int? = null
)