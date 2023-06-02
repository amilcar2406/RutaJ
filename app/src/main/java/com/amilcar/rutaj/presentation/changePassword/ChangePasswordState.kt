package com.amilcar.rutaj.presentation.changePassword

import androidx.annotation.StringRes

data class ChangePasswordState(
   val usuarioId :String = "",
   val email :String = "",
   val nombre :String = "",
   val telefono :String = "",
   val password :String = "",
   val estado :Boolean = false,
   val displayProgressBar :Boolean = false,
   val successChange:Boolean = false,
   @StringRes val errorMessage : Int? = null
)