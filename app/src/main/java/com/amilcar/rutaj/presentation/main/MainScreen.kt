package com.amilcar.rutaj.presentation.main

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Exposure
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Policy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.components.BottomNavigationBar
import com.amilcar.rutaj.presentation.components.Drawer
import com.amilcar.rutaj.presentation.components.TopBar
import com.amilcar.rutaj.presentation.navigation.Destinations
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun MainScreen(
    navController : NavHostController,
    onDenuncia : () -> Unit,
    onOperaciones : () -> Unit,
    onReclamo : () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onWeb: (Context) -> Unit,
    onFacebook: (Context) -> Unit,
    viewModel : MainViewModel,
) {


    val context = LocalContext.current
    val state = viewModel.state

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.primary
    )

    LaunchedEffect(key1 = true){
       viewModel.leerDataStore(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Center


    ) {

        //  inicio variables scaffold
        val scaffoldState = rememberScaffoldState(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        )

        val scope = rememberCoroutineScope()

        val navigationItems = listOf(
            Destinations.CuentasVinculadas,
            Destinations.Reclamo
        )

        val drawerItems = listOf(
            Destinations.VincularCuentas,
            Destinations.CuentasVinculadas,
            Destinations.SimuladorConsumos,
            Destinations.SeguridadELectrica,
            Destinations.Denuncia,
            Destinations.LinkInteres,
            Destinations.AreaConcesion,
            Destinations.Contacto,
        )



        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    items = navigationItems
                )
            },
            topBar = {
                TopBar(
                    scope = scope,
                    scaffoldState = scaffoldState,
                    onWeb = {onWeb(context)},
                    onFacebook =  {onFacebook(context)},
                    nombre = state.value.nombre,
                )
            },
            drawerContent = {
                Drawer(
                    scope,
                    scaffoldState,
                    navController,
                    drawerItems,
                    onNavigateToPerfil = onNavigateToPerfil,
                    nombre = state.value.nombre
                )
            },
            drawerGesturesEnabled = true,
            drawerElevation = 12.dp,
            drawerShape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)


        ) { paddingValues ->
        ConstraintLayout {
             val (button) = createRefs()

            CargarFondo(paddingValues)

            CargarCard(Modifier.padding(paddingValues),
                        onClickOperaciones = onOperaciones,
                        onClickReclamo = onReclamo)



           Row(modifier = Modifier
               .constrainAs(button) {
                   bottom.linkTo(parent.bottom)
               }
               .padding(bottom = 70.dp)
               .fillMaxWidth(),
               horizontalArrangement = Arrangement.Center
           ) {



               OutlinedButton(
                   onClick = { onDenuncia() },
                   colors = ButtonDefaults.buttonColors(
                         backgroundColor = MaterialTheme.colors.secondaryVariant
                   ),
                   shape = RoundedCornerShape(50),
               ) {

                   Icon(
                    imageVector = Icons.Filled.Policy,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                   )

                   Spacer(Modifier.size(ButtonDefaults.IconSpacing))


                   Text(text = "Denunciá un Hurto",
                       fontStyle = FontStyle.Italic,
                       color = MaterialTheme.colors.onPrimary
                   )
               }
           }

        }

            /*   val listafotos = listOf(
                   R.drawable.foto1,
                   R.drawable.foto2,
                   R.drawable.foto3,
                   R.drawable.foto1,
                   R.drawable.foto2,
                   R.drawable.foto3,
               )

               LazyRow(
                   contentPadding = PaddingValues(),
                   horizontalArrangement = Arrangement.spacedBy(4.dp),
                   modifier = Modifier.constrainAs(lazyrow) {
                       bottom.linkTo(parent.bottom)
                   }
               ) {
                   items(listafotos.size) { foto ->
                       CargaGridFotos(foto)
                   }

               }*/


        }
    }

}

/*@Composable
fun CargaGridFotos(foto : Int) {
    Column() {

        Text(text = "Pronunciamiento", textAlign = TextAlign.Left, color = Color.White)

        Image(
            modifier = Modifier.height(250.dp),
            painter = painterResource(id = R.drawable.foto1),
            contentDescription = "foto"
        )
    }

}*/


@ExperimentalCoilApi
@Composable
fun CargarFondo(paddingValues : PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {


        val painter : ImagePainter = if(isSystemInDarkTheme()){
            rememberImagePainter(
                data = R.drawable.fondoappdark
            )
        }else{
            rememberImagePainter(
                data = R.drawable.fondoapp
            )
        }

        val painterState = painter.state

        Image(
            painter = painter,
            contentDescription = "fondo aplicattion",
            contentScale = ContentScale.FillBounds,
            alpha = 0.8f,
            modifier = Modifier
                .fillMaxSize()
                .align(CenterHorizontally),
        )

        if (painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .scale(0.9f)
                    .align(CenterHorizontally)
            )
        } else if (painterState is ImagePainter.State.Success) {
            LaunchedEffect(key1 = painter) {
                launch {
                    //          val image = painter.imageLoader.execute(painter.request).drawable
                    //  viewModel.calcDominantColor(image!!) {
                    //     dominantColor = it
                }
            }
        }
    }
}

@Composable
fun CargarCard(
    modifier : Modifier = Modifier,
    elevation : Dp = 0.dp,
    border : BorderStroke? = null,
    background : Color = Color(0x4da6e1),
    contentColor : Color = contentColorFor(background),
    shape : Shape = MaterialTheme.shapes.large,
    onClickOperaciones :() -> Unit,
    onClickReclamo :() -> Unit,
) {

    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier
            .width(400.dp)
            .height(500.dp)
            .padding(top = 60.dp, start = 20.dp, end = 20.dp)
    ) {
        // Contenedor
        Column {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Coop. Serv. Pcos.", style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ruta J Ltda.", style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.Bold
                )

            }


            Spacer(modifier = Modifier.height(40.dp))

            Row {
                Column(
                    Modifier
                        .width(35.dp)
                        .padding(start = 15.dp, top = 15.dp)
                ) {
                    // icono de fila
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "telefono",
                        tint = Color.White
                    )
                }


                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    // Subtítulo
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "Tenés problemas con el servicio ?",
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )
                    }

                    // Encabezado
                    Text(
                        text = "Realizá un reclamo", style = MaterialTheme.typography.h6,
                        modifier = Modifier.clickable { onClickReclamo()},
                        color = Color.White
                    )

                }

            }

             Divider(color = Color.White, thickness = 1.dp)

             Divider(modifier = Modifier.height(30.dp), color = Color.Transparent)

             Row {
                 Column(
                     Modifier
                         .width(35.dp)
                         .padding(start = 15.dp, top = 15.dp)
                 ) {
                     // icono de fila
                     Icon(
                         imageVector = Icons.Filled.Exposure,
                         contentDescription = "deuda",
                         tint = Color.White
                     )
                 }


                 Column(
                     Modifier
                         .fillMaxWidth()
                         .padding(start = 20.dp, top = 10.dp)
                 ) {
                     // Subtítulo
                     CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                         Text(
                             text = "Querés conocer tu Estado de Cuenta ?",
                             style = MaterialTheme.typography.body1,
                             color = Color.White
                         )
                     }

                     // Encabezado
                     Text(
                         text = "Verificá y Pagá tus facturas", style = MaterialTheme.typography.h6,
                         modifier = Modifier.clickable { onClickOperaciones()},
                         color = Color.White
                     )

                 }

             }

                 Divider(color = Color.White, thickness = 1.dp)
                 Divider(modifier = Modifier.height(30.dp), color = Color.Transparent)

                 Row {
                     Column(
                         Modifier
                             .width(35.dp)
                             .padding(start = 15.dp, top = 15.dp)
                     ) {
                         // icono de fila
                         Icon(
                             imageVector = Icons.Filled.Download,
                             contentDescription = "descargar",
                             tint = Color.White
                         )
                     }


                     Column(
                         Modifier
                             .fillMaxWidth()
                             .padding(start = 20.dp, top = 10.dp)
                     ) {
                         // Subtítulo
                         CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                             Text(
                                 text = "Necesitas descargar tus facturas ?",
                                 style = MaterialTheme.typography.body1,
                                 color = Color.White
                             )
                         }

                         // Encabezado
                         Text(
                             text = "Descargalas Aquí", style = MaterialTheme.typography.h6,
                             modifier = Modifier.clickable { onClickOperaciones()},
                             color = Color.White
                         )
                     }
                 }
            Divider(color = Color.White, thickness = 1.dp)



            //  Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {


            /*   CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

                   Box(
                       Modifier
                           .padding(horizontal = 8.dp)
                           .fillMaxWidth()
                   ) {

                       // Botones
                       Row(modifier = Modifier.align(Alignment.CenterStart)) {

                           TextButton(onClick = { /*TODO*/ }) {
                               Text(text = "ACCIÓN 1")
                           }

                           Spacer(modifier = Modifier.width(8.dp))

                           TextButton(onClick = { /*TODO*/ }) {
                               Text(text = "ACCIÓN 2")
                           }
                       }

                       // Iconos
                       Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                           IconButton(onClick = { /*TODO*/ }) {
                               Icon(Icons.Default.Favorite, contentDescription = null)
                               }

                           IconButton(onClick = { /*TODO*/ }) {
                               Icon(Icons.Default.Share, contentDescription = null)
                           }
                       }
                   }*/
        }
    }
}





