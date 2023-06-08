package com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.amilcar.rutaj.presentation.components.FlechaAtras


@Composable
fun CargarElementosScreen(
    state : CargarElementoElectricoState,
    onBack : () -> Unit,
    viewModel : CargarElementoElectricoViewModel
) {

   val context = LocalContext.current

    LaunchedEffect(key1= state.refreshListSimulador) {
        viewModel.recuperarId(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
        ) {

            FlechaAtras(onBack = onBack, text = "Carga de elementos")

            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = state.categoria,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold

            )

            LazyColumn {
                item { CardAgregarElemento(state, viewModel,context) }
            }
        }
    }
}


@Composable
fun CardAgregarElemento(
    state : CargarElementoElectricoState,
    viewModel : CargarElementoElectricoViewModel,
    context : Context

) {

    val heightCard = 150 + (state.listaSimulador.size * 50)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightCard.dp)
            .padding(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ) {

            Botton("Agregar", {
                viewModel.mostrarCardAgregarElementos()
                viewModel.listarElectrodomesticos(state.categoria,context)
            }, Modifier,true)


            // carga lista del simulador
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)) {
                items(state.listaSimulador.size) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(2.dp),
                        shape = RoundedCornerShape(20),
                        backgroundColor = MaterialTheme.colors.primary,
                        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary)
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(59.dp)
                            .padding(top = 2.dp),
                        verticalAlignment = Alignment.CenterVertically) {

                            Column(
                                modifier = Modifier
                                    .weight(2F),
                            ) {

                                Text(
                                    modifier = Modifier
                                        .padding(top = 7.dp, start = 10.dp)
                                        .fillMaxSize(),
                                    text = state.listaSimulador[item].simulador_detalle,
                                    fontSize = 14.sp
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .weight(1F),
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(top = 7.dp, end = 30.dp)
                                        .fillMaxSize(),
                                    text = "${state.listaSimulador[item].simulador_consumo} kwh",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.End
                                )

                            }

                            Column(modifier = Modifier
                                .weight(0.5F),) {
                                IconButton(onClick = {
                                    viewModel.borrarElemento(state.listaSimulador[item].simulador_id,context)
                                }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "borra elemento",
                                        tint = Color.Cyan
                                    )
                                }
                            }


                        }

                    }
                }
            }
        }
    }


    if (state.mostrarCardAgregarElemento) {
        Spacer(modifier = Modifier.height(10.dp))
        AddElemento(
            state = state,
            onCancelar = { viewModel.cancelarValores() },
            onAceptar = {
                viewModel.guardarElemento(context)
                viewModel.mostrarCardAgregarElementos()
            },
            viewModel = viewModel
        )
    }

}


@Composable
fun AddElemento(
    state : CargarElementoElectricoState,
    onCancelar : () -> Unit,
    onAceptar : () -> Unit,
    viewModel : CargarElementoElectricoViewModel
) {


    val focusRequester = remember { FocusRequester() }
    //val focusManager = LocalFocusManager.current


    if (state.mostrarListaDeElectrodomesticos) {
        if (state.mostrarPopUp) {
            PopUpCustom(state, viewModel, "electrodomesticos")
        }
    }


    if (state.mostrarListaDeCantidades) {
        if (state.mostrarPopUp) {
            PopUpCustom(state, viewModel, "cantidades")
        }
    }

    if (state.mostrarListaDeHoras) {
        if (state.mostrarPopUp) {
            PopUpCustom(state, viewModel, "horasDeUso")
        }
    }



    Surface {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(2.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp),
        ) {


            Column(
                modifier = Modifier.fillMaxSize()
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    //.clickable { viewModel.CambiarEstadoDeLista("electrodomesticos") },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            // The onFocusChanged should be added BEFORE the focusable that is being observed.
                            .onFocusChanged {
                                if (it.isFocused) {
                                    viewModel.cambiarEstadoDeLista("electrodomesticos")
                                    // focusManager.moveFocus(FocusDirection.Down)
                                }
                            },
                        value = state.electrodomesticoValue,
                        onValueChange = {},
                        label = {
                            Text(text = "Electrodomestico")
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.cambiarEstadoDeLista("electrodomesticos")
                                }) {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "arrow drop dwon Icon"
                                )
                            }
                        },

                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onError
                        )
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        modifier = Modifier
                            .width(138.dp)
                            .focusRequester(focusRequester)
                            // The onFocusChanged should be added BEFORE the focusable that is being observed.
                            .onFocusChanged {
                                if (it.isFocused) {
                                    viewModel.cambiarEstadoDeLista("cantidad")
                                    //    focusManager.moveFocus(FocusDirection.Down)
                                }
                            },
                        value = state.cantidadValue,
                        onValueChange = {},
                        label = {
                            Text(text = "Cantidad",
                                 fontSize = 12.sp)
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.cambiarEstadoDeLista("cantidad")
                                }) {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "arrow drop dwon Icon"
                                )
                            }
                        },

                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onError
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    TextField(
                        modifier = Modifier
                            .width(245.dp)
                            .focusRequester(focusRequester)
                            // The onFocusChanged should be added BEFORE the focusable that is being observed.
                            .onFocusChanged {
                                if (it.isFocused) {
                                    viewModel.cambiarEstadoDeLista("horasDeUso")
                                    //    focusManager.moveFocus(FocusDirection.Down)
                                }
                            },
                        value = state.horasDeUsoValue,
                        onValueChange = {},
                        label = {
                            Text(text = "Hs. de uso diario",
                                 fontSize = 12.sp)
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.cambiarEstadoDeLista("horasDeUso")
                                }) {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "arrow drop dwon Icon"
                                )
                            }
                        },

                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onError
                        )
                    )
                }




                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Botton("Cancelar", { onCancelar() }, Modifier,true)

                    Spacer(modifier = Modifier.width(10.dp))

                    Botton("Aceptar", { onAceptar() }, Modifier, enabled = (state.electrodomesticoValue!="" && state.cantidadValue!="" && state.horasDeUsoValue!=""))

                }

            }
        }

    }

}

@Composable
fun Botton(
    titulo : String,
    onClick : () -> Unit,
    modifier : Modifier,
    enabled : Boolean

) {

    Button(
        modifier = modifier
            .width(160.dp)
            .height(50.dp)
            .padding(top = 10.dp, start = 10.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(50),
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        enabled = enabled
    ) {


        when (titulo) {
            "Agregar" -> Icon(imageVector = Icons.Filled.Add, contentDescription = "mas")
            "Cancelar" -> Icon(imageVector = Icons.Filled.Cancel, contentDescription = "cancelar")
            "Aceptar" -> Icon(
                imageVector = Icons.Filled.ConfirmationNumber,
                contentDescription = "aceptar"
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            modifier = Modifier.fillMaxSize(),
            text = titulo,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )


    }
}


@Composable
fun ListaElectrodomesticos(
    state : CargarElementoElectricoState,
    viewModel : CargarElementoElectricoViewModel
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
                    .alpha(0.5f),
                text = "Seleccione una opción",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(3.dp))
        }


        items(state.listaElectodomesticos.size) { item ->
            Text(text = state.listaElectodomesticos[item].elect_detalle,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .clickable {
                        viewModel.almacenarValoresDeCampos(
                            "electrodomesticos",
                            state.listaElectodomesticos[item].elect_detalle,
                            state.listaElectodomesticos[item].elect_consumo_hora.toString()
                        )
                        viewModel.cambiarEstadoDeLista("electrodomesticos")
                    })
        }
    }
}


@Composable
fun ListaCantidades(
    viewModel : CargarElementoElectricoViewModel
) {

    val lista = List(15) { 0 }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.secondary)
                        .alpha(0.5f),
                    text = "Seleccione una opción",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(3.dp))

            }


            items(lista.size) { index ->
                Text(text = (index + 1).toString(),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                        .clickable {
                            viewModel.almacenarValoresDeCampos("cantidades", (index + 1).toString())
                            viewModel.cambiarEstadoDeLista("cantidades")
                        }
                )
            }
        }
    }
}


@Composable
fun ListaHorasDeUso(
    viewModel : CargarElementoElectricoViewModel
) {

    val lista = List(26) { 0 }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.secondary)
                        .alpha(0.5f),
                    text = "Seleccione una opción",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(3.dp))

            }

            items(lista.size) { index ->
                Text(text = when (index) {
                    0 -> "15 mins"
                    1 -> "30 mins"
                    else -> (index - 1).toString() + " Hs."
                },
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                        .clickable {
                            viewModel.almacenarValoresDeCampos(
                                "horasDeUso",
                                when (index) {
                                    0 -> "0.25"
                                    1 -> "0.50"
                                    else -> (index - 1).toString()
                                },
                            )
                            viewModel.cambiarEstadoDeLista("horasDeUso")
                        })
            }
        }

    }
}


@Composable
fun PopUpCustom(
    state : CargarElementoElectricoState,
    viewModel : CargarElementoElectricoViewModel,
    tipoLista : String
) {


    Box {

        // on below line we are specifying height and width
        var popupWidth = 420.dp
        val popupHeight = 500.dp
        if (tipoLista != "electrodomesticos") {
            popupWidth = 350.dp
        }

        // on below line we are checking if dialog is open

        // on below line we are updating button
        // title value.

        // on below line we are adding pop up
        Popup(
            // on below line we are adding
            // alignment and properties.
            alignment = Alignment.TopCenter,
            properties = PopupProperties(),
            onDismissRequest = { viewModel.cambiarEstadoDeLista(tipoLista) }

        ) {

            // on the below line we are creating a box.

            Card(
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary,
                border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),

                // adding modifier to it.
                modifier = Modifier
                    .size(popupWidth, popupHeight)
                    .padding(start = 10.dp)
                // on below line we are adding background color
                //   .background(MaterialTheme.colors.surface, RoundedCornerShape(10.dp))
                // on below line we are adding border.
                // .border(2.dp, color = Color.Black, RoundedCornerShape(10.dp))
            ) {

                // on below line we are adding column
                Column(
                    // on below line we are adding modifier to it.
                    modifier = Modifier
                        .fillMaxSize(),
                    //     .padding(horizontal = 20.dp),
                    // on below line we are adding horizontal and vertical
                    // arrangement to it.
                    //horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center
                ) {
                    when (tipoLista) {
                        "electrodomesticos" -> ListaElectrodomesticos(state, viewModel = viewModel)
                        "cantidades" -> ListaCantidades(viewModel)
                        "horasDeUso" -> ListaHorasDeUso(viewModel)
                    }

                }
            }
        }
    }
}

