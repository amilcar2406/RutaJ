package com.amilcar.rutaj.presentation.vincularCuentas

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.amilcar.rutaj.presentation.components.EventDialog
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.components.RoundedButton
import com.amilcar.rutaj.presentation.components.TransparentTextField


@Composable
fun VincularCuentasScreen(
    state : VincularCuentasState,
    viewModel : VincularCuentasViewModel,
    onValidarCuenta : (String, String, Context) -> Unit,
    onNavigate:() -> Unit,
    onBack : () -> Unit,
    onDismissDialog:() -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(true){
        viewModel.recuperarId(context)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.Top
        ) {


            val cuenta = rememberSaveable { mutableStateOf("") }
            val factura = rememberSaveable { mutableStateOf("") }

            val focusManager = LocalFocusManager.current



            FlechaAtras(onBack = onBack, text = "Cuentas vinculadas")


            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.Center
            ) {

                TransparentTextField(
                    textFieldValue = cuenta,
                    textLabel = "Ingrese Cuenta a Vincular",
                    keyboardType = KeyboardType.Number,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    imeAction = ImeAction.Next
                )


                TransparentTextField(
                    textFieldValue = factura,
                    textLabel = "Ingrese un numero de factura de esa cuenta",
                    keyboardType = KeyboardType.Number,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    imeAction = ImeAction.Next
                )

            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {


                RoundedButton(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Validar",
                    displayProgressBar = false,
                    onClick = {
                        onValidarCuenta(cuenta.value, factura.value,context)
                        focusManager.clearFocus()
                    }
                )

            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Cuentas Vinculadas",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))




                ConstraintLayout {
                    val (lazy, puntos, boton) = createRefs()


                    LazyColumn(
                        modifier = Modifier
                            .padding(20.dp)
                            .height(300.dp)
                            .constrainAs(lazy) {
                                // bottom.linkTo(parent.bottom)
                            },
                    ) {
                        items(state.listaConexiones.size) { item ->
                            MostrarCuenta(state.listaConexiones[item])
                        }

                        //  muestro circulos vacios en el lazyrow
                     /*   item {
                            if (state.listaConexiones.size<=1){
                                for (i in 3 downTo state.listaConexiones.size) MostrarCuenta("")
                            }
                        }*/

                    }



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .height(40.dp)
                            .constrainAs(puntos) {
                                top.linkTo(lazy.bottom)
                            },
                        horizontalArrangement = Arrangement.Center
                    ) {

                        MostrarPuntos(state.ventanas)
                    }




                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .constrainAs(boton) {
                                top.linkTo(parent.bottom)
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        RoundedButton(
                            modifier = Modifier.padding(bottom =  80.dp)                                ,
                            text = "Continuar", displayProgressBar = false,
                            enabled = state.listaConexiones.isNotEmpty(),
                            onClick = {onNavigate()}
                        )
                    }
                }
            }

    }

    if (state.errorMessage != null) {
        EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
    }



}

@Composable
fun MostrarPuntos(ventanas : Int) {

    for (i in 1..ventanas ) {

        Box(
            modifier = Modifier
                .size(10.dp)
                .padding(3.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.surface)
                .border(2.dp, MaterialTheme.colors.primaryVariant)
        )

    }

}


@Composable
fun MostrarCuenta(
    cuenta : String
) {

    var nroCuenta =""
    var nombre =""
    val delimitador = ";"

    val cuentaNombre = cuenta.split(delimitador)


    if (cuentaNombre.isNotEmpty()){
        nroCuenta=cuentaNombre.first()
        nombre=cuentaNombre.last ()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp,MaterialTheme.colors.onPrimary)
    )
    {



        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = nroCuenta,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = nombre,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onPrimary
            )
        }

    }


}
