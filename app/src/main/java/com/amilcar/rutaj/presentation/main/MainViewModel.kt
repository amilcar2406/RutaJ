package com.amilcar.rutaj.presentation.main

import android.content.Context
import android.content.Intent
import android.net.Uri
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
class MainViewModel @Inject constructor() : ViewModel() {


    val state : MutableState<MainState> = mutableStateOf(MainState())

    fun web(context:Context){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.rutaj.com.ar")
        )
        urlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(urlIntent)
    }

    fun facebook(context :Context){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.facebook.com/profile?id=100054534531136")
        )
        urlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(urlIntent)
    }

    fun leerDataStore(context :Context) {
        val dataStore = StoreUserData(context)
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getProferences().collect { userdata ->
                state.value = state.value.copy(
                    nombre = userdata.name
                )
            }
        }
    }

}