package com.amilcar.rutaj.presentation.simuladorConsumo.resultados

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.CargarElementoElectricoState
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.CargarElementoElectricoViewModel
import java.text.DecimalFormat

@Composable
fun ResultadosScreen(
    viewModel : CargarElementoElectricoViewModel,
    state : CargarElementoElectricoState,
    onBack : () -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.recuperarId(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        FlechaAtras(onBack = onBack, text = "Tus elementos")

        val df = DecimalFormat("#######.##")

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.background
            ) {


                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = " Consumo bimestral estimado : "+ df.format(state.totalConsumoSimulador) + " kwh ",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )

                 CargarSimulador(state = state)

            }
        }
    }
}

@Composable
fun CargarSimulador(
    state : CargarElementoElectricoState,
) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
// carga lista del simulador
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp)
        ) {
            items(state.listaSimulador.size) { item ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .weight(2F),
                    ) {

                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxSize(),
                            text = state.listaSimulador[item].simulador_cantidad.toString() + " - " + state.listaSimulador[item].simulador_detalle,
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
                                .padding(end = 30.dp)
                                .fillMaxSize(),
                            text = "${state.listaSimulador[item].simulador_consumo} kwh",
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }
                }
                Divider(modifier = Modifier.height(1.dp), thickness = 1.dp)
            }
        }
    }
}