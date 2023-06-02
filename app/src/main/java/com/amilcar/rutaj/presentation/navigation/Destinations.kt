package com.amilcar.rutaj.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Destinations(
    val route: String,
    val arguments: List<NamedNavArgument>,
    val title: String,
    val icon: ImageVector
){
    @SuppressLint("CustomSplashScreen")
    object Splash: Destinations("SplashScreen", emptyList(),"Splash", Icons.Filled.PictureInPicture)
    object Login: Destinations("LoginScreen", emptyList(),"Login", Icons.Filled.Home)
    object Registration: Destinations("RegistrationScreen",emptyList(),"Registración", Icons.Filled.Person)
    object RecoveryPassword: Destinations(
        route = "RecoveryPasswordScreen",
        arguments = listOf(
                            navArgument("email"){ type = NavType.StringType }
                           ),
        title = "RecuperacionPassword",
        icon = Icons.Filled.KeyboardArrowDown
    )
    object Main: Destinations("MainScreen",emptyList(),"Main",Icons.Filled.Home)
    object ChangePassword: Destinations(
        route = "ChangePasswordScreen",
        arguments = listOf(
            navArgument("usuarioId"){ type = NavType.StringType }
        ),
        title = "Cambio de Clave",
        icon = Icons.Filled.Password
    )
    object Denuncia: Destinations("DenunciasScreen",emptyList(),"Denunciá un hurto de energía",Icons.Filled.RecordVoiceOver)
    object Contacto: Destinations("ContactoScreen",emptyList(),"Contacto",Icons.Filled.ContactPhone)
    object LinkInteres: Destinations("LinkInteresScreen",emptyList(),"Link de interes",Icons.Filled.Link)
    object VincularCuentas: Destinations("VincularCuentasScreen",emptyList(),"Vincular Cuentas",Icons.Filled.Attachment)
    object CuentasVinculadas: Destinations(
        route = "CuentasVinculadasScreen",
        arguments = emptyList(),
        title = "Operaciones",
        icon = Icons.Filled.Dashboard
    )

    object DescargaFacturas: Destinations(
        route = "DescargaFacturasScreen",
        arguments = listOf(
            navArgument("cuenta"){ type = NavType.StringType }
        ),
        title = "Descarga de facturas",
        icon = Icons.Filled.Download
    )
    object EstadoCuenta: Destinations(
        route = "EstadoCuentaScreen",
        arguments =  listOf(
            navArgument("cuenta"){ type = NavType.StringType }
        ),
        title = "Estado Cuenta",
        icon = Icons.Filled.Attachment
    )



    object EvolucionConsumos: Destinations(
        route = "EvolucionConsumosScreen",
        arguments =  listOf(
           navArgument("cuenta"){ type = NavType.StringType }
        ),
        title = "Evolución de consumos",
        icon = Icons.Filled.GraphicEq
    )

    object Operaciones: Destinations(
        route = "OperacionesScreen",
        arguments =  listOf(
            navArgument("cuenta"){ type = NavType.StringType }
        ),
        title = "Operaciones",
        icon = Icons.Filled.Dashboard
    )

    object Pagos: Destinations("PagosScreen",emptyList(),"Medios de Pagos",Icons.Filled.Payment)
    object Perfil: Destinations("PerfilScreen",emptyList(),"Mi Perfil",Icons.Filled.Person)
    object Reclamo: Destinations("RecalmoScreen",emptyList(),"Reclamo",Icons.Filled.PhoneCallback)
    object SimuladorConsumos: Destinations("SimuladorConsumosScreen",emptyList(),"Simulador de consumos",Icons.Filled.ElectricalServices)
    object AreaConcesion: Destinations("AreaConcesionScreen",emptyList(),"Area de concesión",Icons.Filled.FormatShapes)
    object SeguridadELectrica: Destinations("SeguridadElectricaScreen",emptyList(),"Seguridad Eléctrica",Icons.Filled.Security)
    object CategoriasElementosElectricos: Destinations("CategoriasElementosElectricosScreen",emptyList(),"Categoría de Elemenos Eléctricos",Icons.Filled.ElectricalServices)
    object CargerElementos: Destinations(
        "CargarElementosScreen",
        arguments =  listOf(
            navArgument("categoria"){ type = NavType.StringType }
        ),
        "Cargar Elemenos Eléctricos",
        Icons.Filled.ElectricalServices)
    object Resultados: Destinations("ResultadosScreen",emptyList(),"Resultados de tus elementos Eléctricos",Icons.Filled.Summarize)
    object PoliticaPrivacidad: Destinations("PoliticaPrivacidadScreen",emptyList(),"Politica de Privacidad",Icons.Filled.PrivacyTip)








    // con parametros
/*    object LecturasScreen: Destinations("LecturasScreen",
        listOf(
            navArgument("password"){ type = NavType.StringType },
        ),
        "Lecturas",Icons.Filled.Settings)*/

    //object BuscarScreen: Destinations("BuscarScreen",emptyList(),"Buscar",Icons.Filled.Search)
    //object CameraScreen: Destinations("CameraScreen",emptyList(),"Camera",Icons.Filled.Camera)

}


