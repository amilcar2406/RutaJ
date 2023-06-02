package com.amilcar.rutaj.presentation.simuladorConsumo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimuladorConsumoViewModel @Inject constructor() : ViewModel() {

    val  state : MutableState<SimuladorConsumoState> = mutableStateOf(SimuladorConsumoState())

fun cambiaHeladeraState(){
    state.value = state.value.copy(cardHeladeraDesplegada = !state.value.cardHeladeraDesplegada)
}

fun cambiaClimatizacionState(){
    state.value = state.value.copy(cardClimatizacionDesplegada =  !state.value.cardClimatizacionDesplegada)
}

fun cambiaIluminacionState(){
   state.value = state.value.copy(cardIluminacionDesplegada =   !state.value.cardIluminacionDesplegada)
}

fun cambiaLavadorasState(){
   state.value = state.value.copy(cardLavadorasDesplegda =  !state.value.cardLavadorasDesplegda)
}

fun cambiaCocinasState(){
   state.value = state.value.copy(cardCocinasDesplegda =  !state.value.cardCocinasDesplegda)
}

fun cambiaTermontaqueState(){
   state.value = state.value.copy(cardTermotanquesDesplegda =  !state.value.cardTermotanquesDesplegda)
}




}