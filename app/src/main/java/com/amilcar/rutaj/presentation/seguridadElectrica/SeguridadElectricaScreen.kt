package com.amilcar.rutaj.presentation.seguridadElectrica

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.amilcar.rutaj.presentation.components.FlechaAtras


@Composable
fun SeguridadElectricaScreen(
    onBack : () -> Unit
) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            FlechaAtras(onBack = onBack, text = "Seguridad Eléctrica")

            LazyColumn(modifier = Modifier.padding(top = 5.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)){

            item() {
                Text(text = "Las instalaciones eléctricas de un inmueble deben cumplir ciertas condiciones para ser consideradas seguras, confiables y eficientes:",
                     style = MaterialTheme.typography.h6,
                     textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(6.dp))

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "check", tint = MaterialTheme.colors.primaryVariant)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Ser realizadas por un electricista calificado, habilitado y matriculado.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "check", tint = MaterialTheme.colors.primaryVariant)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Cumplir la Reglamentación para la Ejecución de Instalaciones Eléctricas en Inmuebles de la Asociación Electrotécnica Argentina.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "check", tint = MaterialTheme.colors.primaryVariant)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Ser aprobadas por las autoridades de aplicación que tienen incumbencia sobre proyectos eléctricos provinciales y/o municipales.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "check", tint = MaterialTheme.colors.primaryVariant)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Utilizar materiales normalizados según IRAM o IEC, ANSI, IEEE, EN.",
                        textAlign = TextAlign.Justify)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "Condiciones básicas de una instalación eléctrica segura",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "El Reglamento de Suministro exige colocar y mantener dispositivos de protección de las instalaciones para contar con una instalación eléctrica segura y confiable. Revisá que tu instalación cumpla con las siguientes condiciones:",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(3.dp))

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Interruptor general", textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Justify)
                }

                Text(modifier = Modifier.padding(start = 15.dp), text = "Deberá tener uno adecuado a la carga y demanda de potencia",
                    textAlign = TextAlign.Justify)

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "check", tint = MaterialTheme.colors.primaryVariant)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Disyuntor diferencial general",textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Justify)
                }

                Text(modifier = Modifier.padding(start = 5.dp),text = "Deberá tener uno de 30mA / 200 ms. Si actúa cortando la energía indica un problema en las instalaciones. Si esto pasa no debe anularse, y hay que llamar a un electricista matriculado para que lo solucione",
                    textAlign = TextAlign.Justify)

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Conductor de puesta a tierra",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Justify
                    )
                }

                Text(modifier = Modifier.padding(start = 5.dp),text = "Por seguridad es muy importante tener un conductor de puesta a tierra con una resistencia inferior a los 10 ohms",
                    textAlign = TextAlign.Justify)

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Interruptor termomagnético",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Justify
                    )
                }

                Text(modifier = Modifier.padding(start = 5.dp),text = "La instalación tiene que tener uno como mínimo por circuito, o una combinación de interruptor termomagnético e interruptor diferencial",
                    textAlign = TextAlign.Justify)

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Circuitos independientes",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Justify
                    )
                }

                Text(modifier = Modifier.padding(start = 5.dp),text = "Debe tener por lo menos dos. Hay que colocar un interruptor por cada circuito ante consumos elevados. También hay que separar las funciones de los circuitos, por ejemplo: uno para los tomacorrientes, otro para la iluminación y dependiendo el nivel de electrificación uno exclusivo para cada artefacto: lavarropas, horno eléctrico, termotanque eléctrico, aire acondicionado, etc.",
                    textAlign = TextAlign.Justify)

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Toma corrientes con puesta tierra",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Justify
                    )
                }

                Text(modifier = Modifier.padding(start = 5.dp),text = "Todos los artefactos deben contar con conexión a tierra en la instalación.",
                    textAlign = TextAlign.Justify)


                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "Revisá las instalaciones y artefactos del hogar",style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "Cuando compres artefactos eléctricos consultá si están certificados por IRAM o equivalentes como UL o CE.",
                    style = MaterialTheme.typography.body2,
                    fontWeight =  FontWeight.Medium,
                    textAlign = TextAlign.Justify)

                Text(text = "Es recomendable hacer revisar la instalación por un electricista matriculado periódicamente, para verificar su estado de conservación y funcionamiento",
                        style = MaterialTheme.typography.body2,
                        fontWeight =  FontWeight.Medium,
                        textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "Fallas que pueden presentarse con más frecuencia",style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "Si detectás alguno de los siguientes problemas en la instalación eléctrica interna del inmueble llamá de inmediato a un electricista matriculado para que lo resuelva.",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(3.dp))

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Falla en la aislación de un conductor.",
                        textAlign = TextAlign.Justify)
                }
                Text(text = "       Por ejemplo, un cable pelado o cuyo aislante está dañado. Es muy peligroso para las personas y en la mayoría de los casos es detectado por el disyuntor diferencial que actuará cortando la energía.",
                    textAlign = TextAlign.Justify)
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Un falso contacto.",textAlign = TextAlign.Justify)
                }

                    Text(text = "       Ocurre cuando se producen cortes de circuito en forma intermitente.",
                        textAlign = TextAlign.Justify)

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Un cortocircuito.",textAlign = TextAlign.Justify)
                }

                    Text(text = "       Ocurre cuando se tocan dos cables o elementos energizados que normalmente se encuentran aislados o protegidos.",
                        textAlign = TextAlign.Justify)

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Un circuito cortado",textAlign = TextAlign.Justify)
                }

                    Text(text = "       Ocurre cuando se corta un cable o se interrumpe la continuidad del circuito en un artefacto.",textAlign = TextAlign.Justify)


                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "Usá de forma segura la electricidad.",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(3.dp))

                Text(text = "En un Inmueble.",style = MaterialTheme.typography.subtitle1,textAlign = TextAlign.Justify)
                Spacer(modifier = Modifier.height(3.dp))

                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Colocá tapas protectoras en los toma corrientes en espacios donde viven niños.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Mantené los artefactos eléctricos fuera del alcance de los niños.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "No uses ningún equipo eléctrico, por ejemplo heladeras, si estás descalzo o con el piso húmedo.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "No uses alargadores o zapatillas en instalaciones permanentes.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "No sobrecargues circuitos conectando varios artefactos a un mismo tomacorriente.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "No desconectes artefactos tirando del cable, hácelo tomando la ficha.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "No cambies lámparas con el artefacto en funcionamiento, cortá siempre la electricidad desde el tablero.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "No uses cables cortados, gastados o que hayan sido reparados.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Evitá usar artefactos eléctricos con las manos mojadas.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Evitá usar artefactos eléctricos cerca de lugares muy húmedos (ducha, bañera, lavatorio, pileta).",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "No conectes la puesta a tierra del lavarropas a ninguna canilla, caño de gas o agua.",
                        textAlign = TextAlign.Justify)
                }
                Row(modifier = Modifier.fillMaxSize().padding(top = 2.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "En caso de ingresar agua en el inmueble por una inundación cortá la electricidad inmediatamente accionando el interruptor general.",
                        textAlign = TextAlign.Justify)
                }
            }
        }
        
    }

}