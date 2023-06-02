package com.amilcar.rutaj.presentation.registration

import androidx.annotation.StringRes

data class RegisterState(
    val successRegister: Boolean = false,
    val displayProgressBar: Boolean = false,
    val recoveriedpassword :Boolean = false,
    @StringRes val errorMessage: Int? = null
)
