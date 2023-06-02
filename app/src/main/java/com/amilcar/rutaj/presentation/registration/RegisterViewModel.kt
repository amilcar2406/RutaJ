package com.amilcar.rutaj.presentation.registration

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.login.StoreUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
  private val repository : RegisterRepository
): ViewModel() {

    val state: MutableState<RegisterState> = mutableStateOf(RegisterState())



    fun register(
        name: String,
        email: String,
        phoneNumber: String,
        password: String,
        confirmPassword: String,
        context : Context
    ) {


        //val context = getApplication(application).applicationContext
        val dataStore = StoreUserData(context)


        val errorMessage = if(name.isBlank() || email.isBlank() || phoneNumber.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            R.string.error_input_empty
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            R.string.error_not_a_valid_email
        } else if(!Patterns.PHONE.matcher(phoneNumber).matches()) {
            R.string.error_not_a_valid_phone_number
        } else if(password != confirmPassword) {
            R.string.error_incorrectly_repeated_password
        } else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = it)
            return
        }

        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)

            delay(2000)

            // inserto en la base de datos el registro del usuario

             repository.singUp(name, email ,phoneNumber, password).onSuccess {
                 state.value = state.value.copy(displayProgressBar = false,successRegister=true)

                 // guado eldatastore
                     dataStore.setPreferences(
                         name,
                         email,
                         password,
                         phoneNumber,
                         true,
                         0)

             }.onFailure{
                 state.value = state.value.copy(displayProgressBar = false,successRegister=false)
             }


        }
    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }


}