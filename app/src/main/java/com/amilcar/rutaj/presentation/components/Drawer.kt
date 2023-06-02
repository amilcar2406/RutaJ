package com.amilcar.rutaj.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amilcar.rutaj.presentation.navigation.Destinations
import com.amilcar.rutaj.presentation.navigation.currentRow
import com.amilcar.rutaj.ui.theme.Cyan50
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState : ScaffoldState,
    navController : NavHostController,
    items:List<Destinations>,
    onNavigateToPerfil: () -> Unit,
    nombre : String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant), ){

        /*Image(
            painter = painterResource(id = R.drawable.fondodrawer),
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentDescription = "agenda",
            contentScale= ContentScale.FillBounds
        )*/



        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 15.dp)){

            Icon( modifier = Modifier.size(32.dp),
                imageVector =  Icons.Filled.Person,
                contentDescription = "Usuario",
                tint = Color.White)

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp),
                text = nombre,
                letterSpacing = 2.sp,
                style = TextStyle(
                    textAlign = TextAlign.Left,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        offset = Offset(8f, 8f),
                        blurRadius = 5f,
                        color = Color.Black.copy(alpha = 0.8f)
                    ),
                ),

                color =  Color.White
            )

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            ) {
            Spacer(modifier = Modifier.width(270.dp))

            ClickableText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    ) {
                        append("Mi Perfil")
                    }
                },
                style = MaterialTheme.typography.body1,

                ) {
                // abre vantena de registro
                onNavigateToPerfil()

                // poner un scaffold con un snackbar para un mensaje
            }

        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))

        Divider(modifier = Modifier
            .fillMaxWidth(), thickness = 1.dp,
            color = Color.White)

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        val currentRoute = currentRow(navController)

        items.forEach { item ->
            DrawerItem(item = item , selected = currentRoute == item.route){
                navController.navigate(item.route){
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}


@Composable
fun DrawerItem(
    item: Destinations,
    selected: Boolean,
    onItemClick: (Destinations)-> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .padding(10.dp)
        .background(if (selected) Color.Black.copy(alpha = 0.25f) else Color.Transparent)
        .padding(8.dp)
        .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector =  item.icon,
            contentDescription = item.title,
            tint = if (selected) Color.LightGray else Color.White
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = item.title,
            style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
            color = if (selected) Color.White else Cyan50
        )

    }



}