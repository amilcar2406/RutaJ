package com.amilcar.rutaj.presentation.descargasFc

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.R.string
import com.amilcar.rutaj.presentation.components.EventDialog
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.amilcar.rutaj.ui.theme.Red400
import java.util.*


@Composable
fun DescargaFacturasScreen(
    state : DescargasFacturasState,
    cuenta : String,
    onBack : () -> Unit,
    onListarFacturas : (Context, String) -> Unit,
    onDismissDialog : () -> Unit,
    viewModel : DescargasFacturasViewModel,
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = state.init) {
        if (state.init) {onListarFacturas(context, cuenta)}
    }


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


            if (state.terminosYCondiciones) {
                TerminosYCondiciones(viewModel)
            }


            FlechaAtras(onBack = onBack, text = " Descarga de facturas")


            Spacer(modifier = Modifier.height(5.dp))


            MostrarTitularCuenta(cuenta = cuenta)


            Column( horizontalAlignment = Alignment.CenterHorizontally) {
                val delimitador = ";"
                val cuentaNombre = cuenta.split(delimitador)


                val nroCuenta = cuentaNombre.first()
                cuentaNombre.last()



                Text(
                    text = "Listado de facturas emitidas ",
                    style = MaterialTheme.typography.h6,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Normal,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(top = 20.dp)
                )

                Spacer(modifier = Modifier.height(3.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                    horizontalAlignment = Alignment.End
                ) {

                    ClickableText(
                        modifier = Modifier
                            .width(150.dp)
                            .padding(top = 5.dp, end = 10.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onSurface,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 15.sp
                                )
                            ) {
                                append("Terminos y condiciones")
                            }
                        },
                        style = MaterialTheme.typography.body1,

                        ) {
                        // abre vantena de terminos y condiciones
                         viewModel.terminosYCondiciones()

                        // poner un scaffold con un snackbar para un mensaje
                    }
                }
                
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, start = 40.dp),

                    ){
                    items(state.listaFactura.sortedDescending()){item ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                viewModel.downloadFacturas(
                                    nroCuenta,
                                    item,
                                    state.listaUrl,
                                    context
                                )
                            }) {

                            Icon(modifier = Modifier.padding(top = 8.dp),
                                imageVector = Icons.Default.FileDownload, contentDescription = "pdf",tint = Red400)

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(text = item,
                                style = MaterialTheme.typography.h6.copy(
                                    color = Red400,
                                    textDecoration = TextDecoration.Underline)
                            )

                        }


                    }
                }

            }


            if (state.errorMessage != null) {
                EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
            }

        }
    }

}

@Composable
fun TerminosYCondiciones(viewModel : DescargasFacturasViewModel ) {
    Column(modifier = Modifier.fillMaxSize()) {

        FlechaAtras(onBack = { viewModel.terminosYCondiciones() }, text = " Términos y condiciones")

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.background
        ) {


            Spacer(modifier = Modifier.height(5.dp))

            LazyColumn(modifier = Modifier.fillMaxSize().padding(2.dp)){
                item{
                    Text(
                        modifier = Modifier.padding(top=8.dp, start = 4.dp, end = 4.dp),
                        text = stringResource(id = string.terminos_y_condiciones_factura_digital),
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.subtitle1,
                        fontSize =  18.sp
                    )
                }
            }
        }
    }
}


@Composable
fun MostrarTitularCuenta(cuenta:String){
    val delimitador = ";"
    val cuentaNombre = cuenta.split(delimitador)


    val nroCuenta = cuentaNombre.first()
    val nombre = cuentaNombre.last()


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(135.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary)
    ){


        Column(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Cuenta Nº $nroCuenta",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 20.sp
            )


            Text(
                text = "Titular :  ",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 6.dp),
                fontSize = 16.sp
            )

            Text(
                text = nombre.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

        }



    }

}


