package com.amilcar.rutaj.presentation.categoriasElementosElectricos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.presentation.components.RoundedButton
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.CargarElementoElectricoState
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.CargarElementoElectricoViewModel
import java.text.DecimalFormat


@Composable
fun CategoriasElementosElectricosScreen(
    onBack : () -> Unit,
    onClick : (String) -> Unit,
    viewModel : CargarElementoElectricoViewModel,
    state : CargarElementoElectricoState,
    onClickResultado : () -> Unit,
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {

            FlechaAtras(onBack = onBack, text = "Elementos Eléctricos")

            LaunchedEffect(true) {
                viewModel.recuperarId(context)
            }


            Spacer(modifier = Modifier.height(5.dp))

            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Seleccione una categoría",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Text(
                    text = "El consumo bimestral calculado será estimado,",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "dependerá de marca, modelo y uso de los elementos instalados.",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Normal,
                )
            }


            CargarCategorias(onClickElemento = onClick,
                             state = state,
                             onClick = onClickResultado)
        }
    }
}

@Composable
fun CargarCategorias(
    onClickElemento : (String) -> Unit,
    state : CargarElementoElectricoState,
    onClick : () -> Unit
)
  {
      val df = DecimalFormat("#######.##")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            TextButton(
                onClick = {onClickElemento("CLIMATIZACION")},
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small,
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    painter = painterResource(id = R.drawable.climatizacion),
                    contentDescription = "climatizacion",
                    contentScale = ContentScale.FillBounds
                )
            }
            TextButton(
                onClick = {onClickElemento("LINEA BLANCA")},
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small,
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    painter = painterResource(id = R.drawable.lineablanca),
                    contentDescription = "linea blanca",
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            TextButton(
                onClick = {onClickElemento("ILUMINACION")},
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small,
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    painter = painterResource(id = R.drawable.iluminacion),
                    contentDescription = "iluminacion",
                    contentScale = ContentScale.FillBounds
                )
            }
            TextButton(
                //onClick ={onClickConsumo(cuenta)},
                onClick = {onClickElemento("ELECTRODOMESTICO")},
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small,
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    painter = painterResource(id = R.drawable.electrodomesticos),
                    contentDescription = "electrodomesticos",
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            TextButton(
                onClick = {onClickElemento("ELECTRONICA")},
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small,
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    painter = painterResource(id = R.drawable.electronica),
                    contentDescription = "electronica",
                    contentScale = ContentScale.FillBounds
                )

            }

            TextButton(
                onClick = {onClickElemento("CUIDADO PERSONAL")},
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small,
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    painter = painterResource(id = R.drawable.secador),
                    contentDescription = "cuidado personal",
                    contentScale = ContentScale.FillBounds
                )
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            TextButton(
                onClick = {onClickElemento("MOTOR ELECTRICO")},
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp),
                border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small,
            ) {
                Image(
                    modifier = Modifier
                        .width(140.dp)
                        .height(110.dp),
                    painter = painterResource(id = R.drawable.motor),
                    contentDescription = "motores",
                    contentScale = ContentScale.FillBounds
                )
            }

        }



        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text = "Consumo bimestral estimado : "+ df.format(state.totalConsumoSimulador) + " kwh",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        RoundedButton(
            text = "Mirá tus resultados",
            displayProgressBar = false,
            onClick = { onClick() }
        )


    }
}

