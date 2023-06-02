package com.amilcar.rutaj.presentation.contacto

import androidx.annotation.StringRes

data class ContactoState(
    val usuarioEmail :String = "",
    val usuarioId : String = "",
    val successDenuncia: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)
