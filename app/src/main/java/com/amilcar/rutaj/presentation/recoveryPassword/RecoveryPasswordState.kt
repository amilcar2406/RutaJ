package com.amilcar.rutaj.presentation.recoveryPassword

import androidx.annotation.StringRes

data class RecoveryPasswordState(
    val successRecovery: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)
