package com.amilcar.rutaj.presentation.estados


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.EventDialog
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.descargasFc.MostrarTitularCuenta
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun EstadoCuentaScreen(
    title : String,
    cuenta : String,
    state : EstadoCuentaState,
    onBack : () -> Unit,
    onDismissDialog : () -> Unit,
    viewModel : EstadoCuentaViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.listarEstadoCuenta(context,cuenta)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        FlechaAtras(onBack = onBack, text = title)

        MostrarTitularCuenta(cuenta = cuenta)


            if (state.sinDeuda) {
                Text(
                    text = "No tiene talones pendientes de pago.",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {


                    items(state.listaEstado.size) { item ->
                        MostrarItemEstado(
                            state.listaEstado[item]
                        )
                    }
                }
            }


        if (state.errorMessage != null) {
            EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
        }
    }

}


@Composable
fun MostrarItemEstado(
    item : String,
) {


    val arr = item.split(";")

    val vto1 = arr[4]
    val vto2 = arr[5]
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val parseador = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val fechaVto1 = parseador.parse(vto1)
    val fechaVto2 = parseador.parse(vto2)

    val vencida =   if (estaVencida(vto2)) {
          "   vencida"
    }else{""}



    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(3.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary)
    )
    {


        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 10.dp, end = 5.dp)
        ) {
           Row(modifier = Modifier.fillMaxWidth(),
             verticalAlignment = Alignment.Bottom) {


                Text(
                    text = "Comprobante : ",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = arr[0],
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = "Cuota : ",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = arr[1],
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = vencida,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    fontSize =16.sp
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Vto 1 :",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = formatter.format(fechaVto1).toString(),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Importe :",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = arr[2],
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Vto 2 :",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = formatter.format(fechaVto2).toString(),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Importe :",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = arr[3],
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontSize =14.sp
                )
            }


        }

    }

    Spacer(modifier = Modifier.height(5.dp))


}


fun estaVencida(fecha : String) : Boolean {

    val formatt = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val parseador = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val fechaVto = parseador.parse(fecha)
    val fechaFormatted = formatt.format(fechaVto)

    val datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val cmp = fechaFormatted.toString().compareTo(datetime.toString())


    return when {
        cmp > 0 -> {
            false
        }
        cmp < 0 -> {
            true
        }
        else -> {
            false
        }
    }

}




        