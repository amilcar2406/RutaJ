package com.amilcar.rutaj.presentation.login

import androidx.annotation.StringRes

data class LoginState(
    val usuarioId: Int = 0 ,
    val nombre: String ="",
    val telefono: String ="",
    val email: String ="",
    val password: String ="",
    val successLogin: Boolean = false,
    val LoginGoogle: Boolean = false,
    val displayProgressBar :Boolean = false,
    val appHasBeenConfigurated :Boolean = false,
    @StringRes val errorMessage: Int? = null,


)
