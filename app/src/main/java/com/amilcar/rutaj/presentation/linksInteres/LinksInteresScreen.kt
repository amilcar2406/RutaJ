package com.amilcar.rutaj.presentation.linksInteres

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.components.FlechaAtras


@Composable
fun LinkInteresScreen(
    onBack : () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {

        val context = LocalContext.current


        val links = listOf(
            "epre",
            "epre_tarifario",
            "epre_instaladores",
            "face",
            "secrataria_energia",
            "secrataria_nacion",
            "muni_caseros",
            "muni_herrera",
            "muni_pronunciamiento",
            "muni_san_justo",
            "muni_cdelu",
            "muni_colonia_elia"
        )


        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {

            FlechaAtras(onBack =  onBack , text = "Links de interés")



            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 5.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp),
            ) {


                items(links.size) { links ->
                    when (links) {
                        0 -> {
                            CargarLink(
                                context = context,
                                description = "epre",
                                url = "https://epre.gov.ar/web/",
                                id = R.drawable.epre
                            )

                        }
                        1 -> {
                            CargarLink(
                                context = context,
                                description = "epre tarifario",
                                url = "http://aplicaciones.epre.gov.ar/tarifario/",
                                id = R.drawable.epre_tarifario
                            )
                        }
                        2 -> {
                            CargarLink(
                                context = context,
                                description = "epre_instaladores",
                                url = "http://aplicaciones.epre.gov.ar/instaladores/",
                                id = R.drawable.epre_instaladores
                            )
                        }
                        3 -> {
                            CargarLink(
                                context = context,
                                description = "facce",
                                url = "https://www.face.coop/",
                                id = R.drawable.face
                            )
                        }
                        4 -> {
                            CargarLink(
                                context = context,
                                description = "sec. energia entre rios",
                                url = "https://www.entrerios.gov.ar/secretariadeenergia/",
                                id = R.drawable.secretaria_enegia_er
                            )
                        }
                        5 -> {
                            CargarLink(
                                context = context,
                                description = "sec. energia nacion",
                                url = "https://www.argentina.gob.ar/economia/energia",
                                id = R.drawable.secretaria_enegia_nacion
                            )
                        }
                        6 -> {
                            CargarLink(
                                context = context,
                                description = "municipalidad de caseros",
                                url = "https://municaseros.gov.ar/",
                                id = R.drawable.muni_caseros
                            )
                        }
                        7 -> {
                            CargarLink(
                                context = context,
                                description = "municipalidad de caseros",
                                url = "https://www.muniherrera.gov.ar/",
                                id = R.drawable.muni_herrera
                            )
                        }
                        8 -> {
                            CargarLink(
                                context = context,
                                description = "municipalidad de pronunciamiento",
                                url = "http://www.pronunciamiento.gob.ar/",
                                id = R.drawable.muni_pronunciamiento
                            )
                        }
                        9 -> {
                            CargarLink(
                                context = context,
                                description = "municipalidad de san justo",
                                url = "https://munisanjusto.gob.ar/",
                                id = R.drawable.muni_san_justo
                            )
                        }
                        10 -> {
                            CargarLink(
                                context = context,
                                description = "municipalidad  de ccncepcion del uruguay",
                                url = "https://cdeluruguay.gob.ar/",
                                id = R.drawable.muni_cdelu
                            )
                        }
                        11 -> {
                            CargarLink(
                                context = context,
                                description = "municipalidad de de colonia elia",
                                url = "https://coloniaelia.gob.ar/",
                                id = R.drawable.muni_colonia_elia
                            )
                        }
                    }


                }


            }
        }
    }

}

@Composable
fun CargarLink(
    context : Context,
    description : String,
    url : String,
    id : Int
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(5.dp),
         shape = RoundedCornerShape(5.dp),
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    )
                    context.startActivity(urlIntent)
                },
            painter = painterResource(id = id),
            contentDescription = description,
            contentScale = ContentScale.FillBounds,
        )

    }


    Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)

}

