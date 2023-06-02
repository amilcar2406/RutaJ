package com.amilcar.rutaj.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amilcar.rutaj.presentation.components.FlechaAtras
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PoliticaPrivacidadSreen (
    onBack : ()-> Unit
){
    val systemUiController = rememberSystemUiController()
    LocalContext.current
    


        systemUiController.setSystemBarsColor(
            color = MaterialTheme.colors.primary
        )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {


        FlechaAtras(onBack = onBack, text = "Política de Privacidad")


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {

                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 2.dp, end = 2.dp),
                    text =   "COOPERATIVA DE SERVICIOS PUBLICOS RUTA J LTDA. \r\n\r\n" +

                            "TERMINOS DE USO Y POLITICA DE PRIVACIDAD. \r\n\r\n" +
                            "TÉRMINOS DE USO. \r\n" +

                    "Cooperativa de Servicios Públicos Ruta J Ltda. (en adelante la “Aplicación” o la “App”) es una aplicación móvil desarrollada por Cooperativa de Servicios Públicos Ruta J Ltda., creada como una aplicación gratuita para los usuarios. Provee un servicio sin costo para el usuario final y está destinada para usarse de esa manera. \r\n\r\n" +

                            "Estos términos de uso son a los fines de informar a los usuarios sobre las políticas con respecto a la recopilación, el uso y la divulgación de información personal en caso de que decidan usar nuestro servicio. \r\n\r\n" +

                            "Se entiende por 'usuario' a quien complete un registro, usando una dirección de correo electrónico y creando una contraseña para el ingreso, o a quien accede usando los servicios de autenticación de Google, no así a quienes no se autentiquen. \r\n\r\n" +

                            "Al acceder a la aplicación, ya sea mediante registro creando un usuario a través de una dirección de correo electrónico, o por medio de los servicios provistos por Google a fines de autenticación, los presentes términos son aplicables a usted, por lo que debe leerlos detenidamente ya que al ingresar los está aceptando. \r\n\r\n" +
                            
                            "No tiene permitido copiar ni modificar la aplicación, no se le permite extraer el código fuente de la aplicación y tampoco hacer versiones derivadas de la misma. \r\n\r\n" +

                            "La aplicación en sí, y todas las marcas comerciales, derechos de autor, derechos de bases de datos y otros derechos de propiedad intelectual relacionados con ella, siguen perteneciendo a Cooperativa de Servicios Públicos Ruta J Ltda.\r\n\r\n" +


                            "Cooperativa de Servicios Públicos Ruta J Ltda. se compromete a que la aplicación sea lo más útil y eficiente posible. Por ese motivo, se reserva el derecho de realizar cambios en la aplicación, en cualquier momento y por cualquier motivo.\r\n\r\n" +

                            "La Aplicación almacena y procesa los datos personales que usted ha proporcionado al momento de su registro (sea mediante registro por email, o mediante autenticación por medio de los servicios de Google Auth). Dichos datos son a efectos de brindar nuestro servicio.\r\n\r\n" +

                            "La aplicación utiliza servicios de terceros, que declaran sus Términos y condiciones. Puede consultarlos en los sitios respectivos:\r\n\r\n" +

                            "Servicios de Google Playr \r\n" +

                            "AdMobr \r\n" +

                            "Firebaser\r\n\r\n" +

                            "Muchas de las funcionalidades de la aplicación requieren una conexión activa a Internet. No asumimos la responsabilidad de que la aplicación funcione correctamente si no tiene acceso a Internet.\r\n\r\n" +

                            "Al usar la Aplicación, usted asume los costos de los datos que pudiera facturar su prestador de telefonía móvil u otros cargos de terceros que deriven de los términos del acuerdo con su proveedor de red móvil. En caso de utilizar Wi-Fi también es de exclusiva responsabilidad del usuario los costos en los que pudiera incurrir. Al usar la aplicación usted acepta la responsabilidad de dichos cargos, incluidos los cargos por roaming si usa la aplicación fuera de su territorio de origen (es decir, región o país) sin desactivar el roaming de datos.\r\n\r\n" +

                            "Del mismo modo, Cooperativa de Servicios Públicos Ruta J Ltda. no puede asumir la responsabilidad por la forma en que usa la Aplicación.\r\n\r\n" +

                            "Es importante tener en cuenta que, aunque nos esforzamos por asegurarnos de que esté actualizada y sea correcta en todo momento, usamos servicios de terceros. Por lo que no aceptamos responsabilidad por cualquier pérdida directa o indirecta que experimente como resultado de confiar completamente en la funcionalidad de la Aplicación.\r\n\r\n" +

                            "En algún momento, es posible que deseemos actualizar la aplicación. Actualmente está disponible para Android: los requisitos para el sistema pueden cambiar, y deberá descargar las actualizaciones si desea seguir usando la Aplicación. No prometemos que siempre actualizaremos la aplicación para que sea funcional con la versión de Android que tiene instalada en su dispositivo. Sin embargo, usted se compromete a aceptar siempre las actualizaciones de la Aplicación cuando se le ofrezcan. También es posible que deseemos dejar de proporcionar la aplicación y podemos por ello quitarla de la tienda de apps sin notificar el cese del servicio. Ante cualquier cese, los derechos y licencias que se le otorgaron en los presentes términos concluirán; debe dejar de usar la aplicación y eliminarla de su dispositivo.\r\n\r\n" +

                            "Los presentes Términos y Condiciones pueden ser cambiados o actualizados de vez en cuando. Por lo tanto, se le recomienda revisar esta página periódicamente para conocer si hubo algún cambio. Así mismo, notificaremos cualquier cambio publicando los nuevos Términos y Condiciones en esta página.\r\n\r\n" +

                            "Los presentes Términos y Condiciones son efectivos a partir del día 01 de junio de 2023.\r\n\r\n" +

                            "Por cualquier duda o sugerencia, no dude en contactarnos mediante correo electrónico a contacto@rutaj.com.ar..\r\n\r\n" +


                            "POLÍTICAS DE PRIVACIDAD \r\n\r\n" +

                            "Las presentes políticas informan al usuario sobre el tratamiento que le damos a la información personal que brinda cuando accede a la Aplicación.\r\n\r\n" +

                            "La aplicación permite al usuario acceder a los datos de la cuenta corriente que el usuario mantiene con la Cooperativa, descargar sus facturas por el servicio eléctrico prestado, conocer sus consumos y realizar pagos a través de sitios de terceros.\r\n\r\n" +

                            "Para poder brindar estos servicios, se requiere un acceso por medio de una cuenta creada en nuestra aplicación, sea mediante el registro por medio de correo electrónico, o mediante los servicios de Google Auth. En ambos casos se almacenan los datos proporcionados voluntariamente por el usuario.\r\n\r\n" +

                            "¿Qué datos almacenamos?\r\n\r\n" +

                            "Almacenamos dirección de correo electrónico, nombre y teléfono, datos requeridos en el registro, del mismo modo cuando el usuario se autentica con Google, almacenamos el nombre que provee el tercero, y la dirección de correo electrónico.\r\n\r\n" +

                            "Los datos son empleados para poder cumplir con la funcionalidad de la aplicación, y no son usados con ningún otro fin. \r\n\r\n" +

                            "Ya que no solicitamos otros datos, no es posible cambiar los del registro.\r\n\r\n" +

                            "Usted puede en cualquier momento solicitar la eliminación de su Cuenta, mediante envío de mail a contacto@rutaj.com.ar, lo que genera la consecuente eliminación de todos sus datos en nuestros registros de la app.\r\n\r\n" +

                            "La APP no registra direcciones IP, no accede a los datos de contacto del dispositivo, no accede a las cuentas de correos de los usuarios, no almacena datos relativos al dispositivo, ni recoge información sobre la ubicación real del usuario.\r\n\r\n" +

                            "Las presentes políticas pueden ser modificadas en cualquier momento, y serán publicadas en esta página.\r\n\r\n" +

                            "Por cualquier duda, consulta o por simple contacto, puede escribirnos a contacto@rutaj.com.ar",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Justify,
                    fontSize = 18.sp
                )
            }
        }
    }
}