package com.amilcar.rutaj.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.twotone.Facebook
import androidx.compose.material.icons.twotone.Public
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable

    fun TopBar(
    scope : CoroutineScope,
    scaffoldState : ScaffoldState,
    onWeb : () -> Unit,
    onFacebook : () -> Unit,
    backgroundColor : Color = MaterialTheme.colors.primary,
    contentColor : Color = contentColorFor(backgroundColor),
    nombre : String,
    ) {



        TopAppBar(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            elevation = 2.dp,
            title = {
                Row {
                    Text(
                        text = "Hola",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                        //    color = MaterialTheme.colors.onBackground
                    )

                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = nombre,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                        //    color = MaterialTheme.colors.onBackground
                    )

                }

            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open() // abre el icnono menu
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                    //    tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            actions = {


                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = {
                       onWeb()
                    }) {
                        Icon(
                            imageVector = Icons.TwoTone.Public,
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = {
                        onFacebook()
                    }) {
                        Icon(
                            imageVector = Icons.TwoTone.Facebook,
                            contentDescription = null
                        )
                    }


                }


            }

        )

    }


