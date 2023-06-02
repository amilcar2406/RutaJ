package com.amilcar.rutaj.presentation.perfil

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.presentation.login.StoreUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
) :ViewModel(){




    val state: MutableState<PerfilState> = mutableStateOf(PerfilState())


    fun leerDataStore(context:Context){
        val dataStore = StoreUserData(context)
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getProferences().collect { userdata ->
                state.value = state.value.copy(
                    usuarioId = userdata.id.toString(),
                    email = userdata.email,
                    nombre= userdata.name,
                    telefono = userdata.telefono,
                    password = userdata.pass,
                )
            }
        }


    }
}