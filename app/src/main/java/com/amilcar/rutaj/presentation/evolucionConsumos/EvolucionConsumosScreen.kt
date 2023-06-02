package com.amilcar.rutaj.presentation.evolucionConsumos

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.descargasFc.MostrarTitularCuenta


@Composable
fun EvolucionConsumosScreen(
    state : EvolucionConsumosState,
    title : String,
    cuenta : String,
    onBack : () -> Unit,
    viewModel : EvolucionConsumosViewModel
) {

    val context = LocalContext.current
    var showValores by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.evolucionConsumos(context,cuenta)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        FlechaAtras(onBack = onBack, text = title)

        MostrarTitularCuenta(cuenta = cuenta)

        Divider(color = Color.White, thickness = 1.dp)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary.copy(0.2f))
                .padding(30.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Evolución de consumos en kwh",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                ) {
                    Text(
                        "Mostrar Kwh",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Switch(
                        checked = showValores,
                        onCheckedChange = {
                            showValores = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colors.onSurface,
                            uncheckedThumbColor = MaterialTheme.colors.onSecondary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                /* val multiplicador = if (input.consumo.toInt()<=topeMaximo){
                     1
                 }else{
                     input.consumo.toInt() / topeMaximo
                 }*/





                if (state.listaEvolucionConsumo.isNotEmpty()) {
                    BarChart(
                        inputList = listOf(
                            BarchartInput(
                                consumo = state.listaEvolucionConsumo[6].consumo,
                                state.listaEvolucionConsumo[6].valorBarra,
                                description = state.listaEvolucionConsumo[6].description
                            ),
                            BarchartInput(
                                consumo = state.listaEvolucionConsumo[5].consumo,
                                state.listaEvolucionConsumo[5].valorBarra,
                                description = state.listaEvolucionConsumo[5].description
                            ),
                            BarchartInput(
                                consumo = state.listaEvolucionConsumo[4].consumo,
                                state.listaEvolucionConsumo[4].valorBarra,
                                description = state.listaEvolucionConsumo[4].description
                            ),
                            BarchartInput(
                                consumo = state.listaEvolucionConsumo[3].consumo,
                                state.listaEvolucionConsumo[3].valorBarra,
                                description = state.listaEvolucionConsumo[3].description
                            ),
                            BarchartInput(
                                consumo = state.listaEvolucionConsumo[2].consumo,
                                state.listaEvolucionConsumo[2].valorBarra,
                                description = state.listaEvolucionConsumo[2].description
                            ),
                            BarchartInput(
                                consumo = state.listaEvolucionConsumo[1].consumo,
                                state.listaEvolucionConsumo[1].valorBarra,
                                description = state.listaEvolucionConsumo[1].description
                            ),
                            BarchartInput(
                                consumo = state.listaEvolucionConsumo[0].consumo,
                                state.listaEvolucionConsumo[0].valorBarra,
                                description = state.listaEvolucionConsumo[0].description
                            ),
                        ),
                        showValores = showValores
                    )
                }else{
                    Text(text = "No tiene consumos para mostrar",
                        fontSize = 20.sp)
                }

            }
        }
    }

}


@Composable
fun BarChart(
    inputList : List<BarchartInput>,
    showValores : Boolean,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        inputList.forEach { input ->
            //val percentage = input.value/listSum.toFloat()

            val consumo = input.consumo
            val valorBarra = input.valorBarra



            Bar(
                modifier = Modifier
                    .height(1.dp * valorBarra)
                    .width(40.dp),
                primaryColor = MaterialTheme.colors.primaryVariant,
                // percentage = input.value/listSum.toFloat(),
                consumo = consumo,
                description = input.description,
                showValores = showValores
            )
        }
    }
}

@Composable
fun Bar(
    modifier : Modifier = Modifier,
    primaryColor : Color,
    // percentage:Float,
    consumo : Int,
    description : String,
    showValores : Boolean
) {


    val colorCuadro : Color = if(isSystemInDarkTheme()) {
        Color.White
    } else {
        Black
    }


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(400.dp)
        ) {


            val width = size.width
            val height = size.height
            val barWidth = width / 5 * 3
            val barHeight = height / 8 * 7
            val barHeight3DPart = height - barHeight
            val barWidth3DPart = (width - barWidth) * (height * 0.002f)


            var path = Path().apply {
                moveTo(0f, height)
                lineTo(barWidth, height)
                lineTo(barWidth, height - barHeight)
                lineTo(0f, height - barHeight)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(Black, primaryColor)
                )
            )
            path = Path().apply {
                moveTo(barWidth, height - barHeight)
                lineTo(barWidth3DPart + barWidth, 0f)
                lineTo(barWidth3DPart + barWidth, barHeight)
                lineTo(barWidth, height)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, Black)
                )
            )
            path = Path().apply {
                moveTo(0f, barHeight3DPart)
                lineTo(barWidth, barHeight3DPart)
                lineTo(barWidth + barWidth3DPart, 0f)
                lineTo(barWidth3DPart, 0f)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(Black, primaryColor)
                )
            )
            // esto dibuja la descripcion debajo del grafico , eje x
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    //"${(percentage*100).roundToInt()} %",
                    description,
                    barWidth / 5f,
                    height + 55f,
                    Paint().apply {
                        this.color = colorCuadro.toArgb()
                        textSize = 11.dp.toPx()
                        isFakeBoldText = true
                    }
                )
            }
            if (showValores) {
                drawContext.canvas.nativeCanvas.apply {
                    rotate(-50f, pivot = Offset(barWidth3DPart - 20, 0f)) {
                        drawText(
                            consumo.toString(),
                            0f,
                            0f,
                            Paint().apply {
                                this.color = colorCuadro.toArgb()
                                textSize = 14.dp.toPx()
                                isFakeBoldText = true
                            }
                        )
                    }
                }
            }
        }
    }
}


data class BarchartInput(
    val consumo : Int,
    val valorBarra : Int,
    val description : String,
)

