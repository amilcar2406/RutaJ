package com.amilcar.rutaj.presentation.areaConcesion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.FlechaAtras

@Composable
fun AreaConcesionScreen(
    onBack : () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {

        FlechaAtras(onBack = onBack, text = "Area de concesión")


        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 2.dp, end = 2.dp),
            text = "Nuestra área de concesión consta de más de 600 kms de Lineas de Media Tensión.\r\n\r\n" +
                    "Prestamos servicios en las Plantas Urbanas de Caseros, " +
                    "Pronunciamiento y Herrera.\r\n\r\n" +
                    "Además de las Colonia Caseros, Colonia Sesteada, " +
                    "Colonia Los Ceibos, Colonia Of. Nro 6, Colonia Santa Ana, Colonia Elía, " +
                    "Colonia Las Marías, Talita, Zona Rural San Justo, Rincón Santa María, " +
                    "Colonia Dolores, Colonia Santa Teresita, Colonia Centenario, Colonia Gral. Urquiza, " +
                    "Colonia Santa Vicenta, Colonia Santa Zelmira, Colonia San Martín," +
                    "Colonia San Rafael, Colonia La Humilde, Colonia San Carlos y Rincón de Gená",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Justify,
            fontSize = 18.sp


        )
    }

}