package com.amilcar.rutaj.presentation.simuladorConsumo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.components.RoundedButton

@Composable
fun SimuladorConsumoScreen(
    state : SimuladorConsumoState,
    onBack : () -> Unit,
    onClick : () -> Unit,
    viewModel : SimuladorConsumoViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        FlechaAtras(onBack = onBack, text = "Simulador de consumos")

        Text(
            text = "El simulador te permitirá ver que artefactos estan consumiendo más y tomar medidas para controlar tu consumo",
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            RoundedButton(
                text = "Ingresá al Simulador",
                displayProgressBar = false,
                onClick = { onClick() }
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Recomendaciones prácticas",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(10.dp))


        LazyColumn {
            item {
                CardRecomendaciones(
                    state = state,
                    viewModel = viewModel,
                    flag = 1,
                    titulo = "Aire Acondicionado y Ventiladores",
                    texto1 = stringResource(R.string.recomendClimatizacionTexto1),
                    texto2 = stringResource(R.string.recomendClimatizacionTexto2),
                    texto3 = stringResource(R.string.recomendClimatizacionTexto3),
                    texto4 = stringResource(R.string.recomendClimatizacionTexto4),
                    texto5 = stringResource(R.string.recomendClimatizacionTexto5),
                )

                Spacer(modifier = Modifier.height(8.dp))

                CardRecomendaciones(
                    state = state,
                    viewModel = viewModel,
                    flag = 2,
                    titulo = "Heladeras y Freezers",
                    texto1 = stringResource(R.string.recomendHeladerasTexto1),
                    texto2 = stringResource(R.string.recomendHeladerasTexto2),
                    texto3 = stringResource(R.string.recomendHeladerasTexto3),
                    texto4 = stringResource(R.string.recomendHeladerasTexto4),
                    texto5 = ""
                )

                Spacer(modifier = Modifier.height(8.dp))

                CardRecomendaciones(
                    state = state,
                    viewModel = viewModel,
                    flag = 3,
                    titulo = "Lavadoras",
                    texto1 = stringResource(R.string.recomendLavarropaTexto1),
                    texto2 = stringResource(R.string.recomendLavarropaTexto2),
                    texto3 = stringResource(R.string.recomendLavarropaTexto3),
                    texto4 = stringResource(R.string.recomendLavarropaTexto4),
                    texto5 = ""
                )

                Spacer(modifier = Modifier.height(8.dp))

                CardRecomendaciones(
                    state = state,
                    viewModel = viewModel,
                    flag = 4,
                    titulo = "Iluminación",
                    texto1 = stringResource(R.string.recomendIluminacionTexto1),
                    texto2 = stringResource(R.string.recomendIluminacionTexto2),
                    texto3 = stringResource(R.string.recomendIluminacionTexto3),
                    texto4 = stringResource(R.string.recomendIluminacionTexto4),
                    texto5 = stringResource(R.string.recomendIluminacionTexto5),
                )

                Spacer(modifier = Modifier.height(8.dp))

                CardRecomendaciones(
                    state = state,
                    viewModel = viewModel,
                    flag = 5,
                    titulo = "Termontanques",
                    texto1 = stringResource(R.string.recomendTermotanquesTexto1),
                    texto2 = stringResource(R.string.recomendTermotanquesTexto2),
                    texto3 = stringResource(R.string.recomendTermotanquesTexto3),
                    texto4 = stringResource(R.string.recomendTermotanquesTexto4),
                    texto5 = stringResource(R.string.recomendTermotanquesTexto5),
                )

                Spacer(modifier = Modifier.height(8.dp))

                CardRecomendaciones(
                    state = state,
                    viewModel = viewModel,
                    flag = 6,
                    titulo = "Cocinas",
                    texto1 = stringResource(R.string.recomendCocinasTexto1),
                    texto2 = stringResource(R.string.recomendCocinasTexto2),
                    texto3 = stringResource(R.string.recomendCocinasTexto3),
                    texto4 = stringResource(R.string.recomendCocinasTexto4),
                    texto5 = stringResource(R.string.recomendCocinasTexto5),
                )

                Spacer(modifier = Modifier.height(8.dp))


            }

        }

    }

}


@Composable
fun CardRecomendaciones(
    state : SimuladorConsumoState,
    viewModel : SimuladorConsumoViewModel,
    flag : Int,
    titulo : String,
    texto1 : String,
    texto2 : String,
    texto3 : String,
    texto4 : String,
    texto5 : String
) {

    var estado = false

    when (flag) {
        1 -> {
            estado = state.cardClimatizacionDesplegada
        }
        2 -> {
            estado = state.cardHeladeraDesplegada
        }
        3 -> {
            estado = state.cardLavadorasDesplegda
        }
        4 -> {
            estado = state.cardIluminacionDesplegada
        }
        5 -> {
            estado = state.cardTermotanquesDesplegda
        }
        6 -> {
            estado = state.cardCocinasDesplegda
        }
    }


    if (!estado) {
//    if (!state.cardHeladeraDesplegada) {

        Card(
            modifier = Modifier
                .height(40.dp)
                .padding(3.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = 8.dp,
            backgroundColor = MaterialTheme.colors.secondary.copy(0.7f),
            contentColor = MaterialTheme.colors.onBackground,
            border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "mas",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.clickable {
                        when (flag) {
                            1 -> viewModel.cambiaClimatizacionState()
                            2 -> viewModel.cambiaHeladeraState()
                            3 -> viewModel.cambiaLavadorasState()
                            4 -> viewModel.cambiaIluminacionState()
                            5 -> viewModel.cambiaTermontaqueState()
                            6 -> viewModel.cambiaCocinasState()
                        }

                    }
                )


                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = titulo,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary
                )

            }

        }
    } else {
        Card(
            modifier = Modifier
                .height(400.dp)
                .padding(3.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = 8.dp,
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onBackground,
            border = BorderStroke(2.dp, MaterialTheme.colors.onSurface)
        ) {

            LazyColumn {
                item() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Undo,
                            contentDescription = "menos",
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier.clickable {
                                when (flag) {
                                    1 -> viewModel.cambiaClimatizacionState()
                                    2 -> viewModel.cambiaHeladeraState()
                                    3 -> viewModel.cambiaLavadorasState()
                                    4 -> viewModel.cambiaIluminacionState()
                                    5 -> viewModel.cambiaTermontaqueState()
                                    6 -> viewModel.cambiaCocinasState()
                                }
                            }
                        )


                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = titulo,
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.primary
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = texto1,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colors.onSecondary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = texto2,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colors.onSecondary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = texto3,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colors.onSecondary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = texto4,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colors.onSecondary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = texto5,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colors.onSecondary
                        )
                    }
                }


            }


        }
    }
}

