package com.amilcar.rutaj.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.amilcar.rutaj.presentation.areaConcesion.AreaConcesionScreen
import com.amilcar.rutaj.presentation.categoriasElementosElectricos.CategoriasElementosElectricosScreen
import com.amilcar.rutaj.presentation.changePassword.ChangePasswordScreen
import com.amilcar.rutaj.presentation.changePassword.ChangePasswordViewmodel
import com.amilcar.rutaj.presentation.contacto.ContactoScreen
import com.amilcar.rutaj.presentation.contacto.ContactoViewModel
import com.amilcar.rutaj.presentation.cuentasVinculadas.CuentasVinculadasViewModel
import com.amilcar.rutaj.presentation.descargasFc.CuentasVinculadasScreen
import com.amilcar.rutaj.presentation.descargasFc.DescargaFacturasScreen
import com.amilcar.rutaj.presentation.descargasFc.DescargasFacturasViewModel
import com.amilcar.rutaj.presentation.estados.EstadoCuentaScreen
import com.amilcar.rutaj.presentation.estados.EstadoCuentaViewModel
import com.amilcar.rutaj.presentation.evolucionConsumos.EvolucionConsumosScreen
import com.amilcar.rutaj.presentation.evolucionConsumos.EvolucionConsumosViewModel
import com.amilcar.rutaj.presentation.linksInteres.LinkInteresScreen
import com.amilcar.rutaj.presentation.login.*
import com.amilcar.rutaj.presentation.main.MainScreen
import com.amilcar.rutaj.presentation.main.MainViewModel
import com.amilcar.rutaj.presentation.operaciones.OperacionesScreen
import com.amilcar.rutaj.presentation.pagos.PagosScreen
import com.amilcar.rutaj.presentation.perfil.PerfilScreen
import com.amilcar.rutaj.presentation.perfil.PerfilViewModel
import com.amilcar.rutaj.presentation.reclamo.ReclamoScreen
import com.amilcar.rutaj.presentation.reclamo.ReclamosViewmodel
import com.amilcar.rutaj.presentation.recoveryPassword.RecoveryPasswordViewModel
import com.amilcar.rutaj.presentation.registration.RegisterViewModel
import com.amilcar.rutaj.presentation.registration.RegistrationScreen
import com.amilcar.rutaj.presentation.seguridadElectrica.SeguridadElectricaScreen
import com.amilcar.rutaj.presentation.simuladorConsumo.SimuladorConsumoScreen
import com.amilcar.rutaj.presentation.simuladorConsumo.SimuladorConsumoViewModel
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.CargarElementoElectricoViewModel
import com.amilcar.rutaj.presentation.simuladorConsumo.cargarElementos.CargarElementosScreen
import com.amilcar.rutaj.presentation.simuladorConsumo.resultados.ResultadosScreen
import com.amilcar.rutaj.presentation.splash.SplashScreen
import com.amilcar.rutaj.presentation.vincularCuentas.VincularCuentasScreen
import com.amilcar.rutaj.presentation.vincularCuentas.VincularCuentasViewModel
import com.amilcar.rutaj.util.Variables
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun Navigation() {


    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash.route
    )
    {
        addSplash(navController)
        addLogin(navController)
        addRegistration(navController)
        addRecoveryPassword(navController)
        addMainScreen(navController)
        addChangePasswordScreen(navController)
        addDenunciaScreen(navController)
        addContactoScreen(navController)
        addLinkInteresScreen(navController)
        addVincularCuentasScreen(navController)
        addCuentasVinculadasScreen(navController)
        addDescargaFacturasScreen(navController)
        addOperacionesScreen(navController)
        addEstadoCuentaScreen(navController)
        addEvolucionConsumosScreen(navController)
        addPagosScreen(navController)
        addPerfilScreen(navController)
        addReclamoScreen(navController)
        addAreaConcesionScreen(navController)
        addSeguridadElectricaScreen(navController)
        addSimuladorConsumoScreen(navController)
        addCategoriasElementosElectricosScreen(navController)
        addCargarElementosScreen(navController)
        addResultadosScreen(navController)
        addPoliticaPrivacidadScreen(navController)
    }

}


@ExperimentalAnimationApi
fun NavGraphBuilder.addSplash(
    navController : NavHostController
) {
    composable(
        route = Destinations.Splash.route
    ) {
        SplashScreen(navController)
    }
}


@ExperimentalPermissionsApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addLogin(
    navController : NavHostController,
    /*   email : String?,
       id : String?,
       nombre : String?*/
) {
    composable(
        route = Destinations.Login.route
    ) {


// verifico si ya se ha logueado con google y tiene la sesion iniciadas

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.

        // si email viene con datos es porque el vago ya se loggeo en google
        /*  email?.let {
                 // verficar si esta dado de alta en la base de datos sino darlo de alta
              Log.d("mica",email)
              Log.d("mica",id!!)
              Log.d("mica",nombre!!)

              viewModel.state.value.copy(successLogin = true)
          }*/


        val viewModel : LoginViewModel = hiltViewModel()

        LoginScreen(
            viewModel = viewModel,
            navController = navController,
            state = viewModel.state.value,
            onLogin = viewModel::login,
            onNavigateToRegister = {
                navController.navigate(Destinations.Registration.route)
            },
            onNavigatetoRecoveyPassword = { mail ->
                navController.navigate(Destinations.RecoveryPassword.route + "/$mail")
            },
            onDismissDialog = viewModel::hideErrorDialog,
        )
    }

    // }*/
}


@OptIn(ExperimentalCoilApi::class)
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addMainScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.Main.route
    ) {
        val viewModel : MainViewModel = hiltViewModel()

        MainScreen(
            navController,
            onDenuncia = { navController.navigate(Destinations.Denuncia.route) },
            onOperaciones = { navController.navigate(Destinations.CuentasVinculadas.route) },
            onReclamo = { navController.navigate(Destinations.Reclamo.route) },
            onNavigateToPerfil = { navController.navigate(Destinations.Perfil.route) },
            onWeb = viewModel::web,
            onFacebook = viewModel::facebook,
            viewModel = viewModel,
        )
    }
}


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addRegistration(
    navController : NavHostController,
) {
    composable(
        route = Destinations.Registration.route
    )
    {


        val viewModel : RegisterViewModel = hiltViewModel()
        val viewModelLogin : LoginViewModel = hiltViewModel()


        RegistrationScreen(
            stateLogin = viewModelLogin.state.value,
            state = viewModel.state.value,
            navController = navController,
            onRegister = viewModel::register,
            onBack = {
                navController.popBackStack()
            },
            onLogin = viewModelLogin::login,
            onDismissDialog = viewModel::hideErrorDialog,
            viewModel = viewModelLogin

        )

    }
}


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addRecoveryPassword(
    navController : NavHostController,
) {
    composable(
        route = Destinations.RecoveryPassword.route + "/{email}",
    )
    {
        val email = it.arguments?.getString("email")
        val viewModel : RecoveryPasswordViewModel = hiltViewModel()


        RecoveryPasswordScreen(
            state = viewModel.state.value,
            email = email.toString(),
            onRecovery = viewModel::validarMail,
            onBack = {
                navController.popBackStack()
            },
            onDismissDialog = viewModel::hideErrorDialog
        )

    }
}


@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addChangePasswordScreen(
    navController : NavHostController,
) {

    composable(
        // route = Destinations.LecturasScreen.route + "/{password}",
        //  arguments = Destinations.LecturasScreen.arguments
        route = Destinations.ChangePassword.route + "/{usuarioId}"
    )
    {
        val usuarioId = it.arguments?.getString("usuarioId")
        val viewModel : ChangePasswordViewmodel = hiltViewModel()
        val state = viewModel.state
        state.value = state.value.copy(usuarioId = usuarioId.toString())

        ChangePasswordScreen(
            state = state.value,
            onChangePass = viewModel::changePassword,
            onBack = {
                navController.navigate(route = Destinations.Perfil.route)
                navController.popBackStack(Destinations.ChangePassword.route, true)
            },
            onDismissDialog = viewModel::hideErrorDialog
        )

    }
}

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addDenunciaScreen(
    navController : NavHostController,
) {

    composable(
        // route = Destinations.LecturasScreen.route + "/{password}",
        //  arguments = Destinations.LecturasScreen.arguments
        route = Destinations.Denuncia.route
    )
    {
        val viewModel : ContactoViewModel = hiltViewModel()

        ContactoScreen(
            tipoContacto = "denuncia",
            onContacto = viewModel::contacto,
            onBack = { navController.popBackStack() },
            onDismissDialog = viewModel::hideErrorDialog,
            viewModel = viewModel
        )

    }
}

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addContactoScreen(
    navController : NavHostController,
) {

    composable(
        // route = Destinations.LecturasScreen.route + "/{password}",
        //  arguments = Destinations.LecturasScreen.arguments
        route = Destinations.Contacto.route
    )
    {
        val viewModel : ContactoViewModel = hiltViewModel()

        ContactoScreen(
            tipoContacto = "contacto",
            viewModel = viewModel,
            onContacto = viewModel::contacto,
            onBack = { navController.popBackStack() },
            onDismissDialog = viewModel::hideErrorDialog
        )

    }
}

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addLinkInteresScreen(
    navController : NavHostController,
) {

    composable(
        // route = Destinations.LecturasScreen.route + "/{password}",
        //  arguments = Destinations.LecturasScreen.arguments
        route = Destinations.LinkInteres.route
    )
    {

        LinkInteresScreen(
            onBack = { navController.popBackStack() },
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addVincularCuentasScreen(
    navController : NavHostController
) {

    composable(
        // route = Destinations.LecturasScreen.route + "/{password}",
        //  arguments = Destinations.LecturasScreen.arguments
        route = Destinations.VincularCuentas.route
    )
    {
        val viewModel : VincularCuentasViewModel = hiltViewModel()

        VincularCuentasScreen(
            state = viewModel.state.value,
            viewModel = viewModel,
            onValidarCuenta = viewModel::validarCuenta,
            onNavigate = {
                if (Variables.G_hasBeenConfiguratedBefore) {
                    navController.popBackStack()
                } else {
                    navController.navigate(
                        route = Destinations.Main.route
                    )
                }
            },
            onBack = {
                if (Variables.G_hasBeenConfiguratedBefore) {
                    navController.popBackStack()
                } else {
                    navController.navigate(
                        route = Destinations.Main.route
                    )
                }
            },
            onDismissDialog = viewModel::hideErrorDialog
        )
    }
}


fun NavGraphBuilder.addCuentasVinculadasScreen(
    navController : NavHostController
) {
    composable(
        // route = Destinations.LecturasScreen.route + "/{password}",
        //  arguments = Destinations.LecturasScreen.arguments
        route = Destinations.CuentasVinculadas.route
    )
    {
        val viewModel : CuentasVinculadasViewModel = hiltViewModel()

        CuentasVinculadasScreen(
            title = "Cuentas Vinculadas",
            subtitle = "Selecciona una cuenta para operar",
            state = viewModel.state.value,
            /*    onNavigate = {
                    navController.navigate(
                        route = Destinations.Main.route
                    )
                },*/
            clickActivo = true,
            onClickCuentas = { cuenta ->
                navController.navigate(Destinations.Operaciones.route + "/$cuenta")
            },
            onBack = { navController.popBackStack() },
            onDismissDialog = viewModel::hideErrorDialog,
            viewModel = viewModel
        )
    }
}


fun NavGraphBuilder.addDescargaFacturasScreen(
    navController : NavHostController
) {
    composable(
        // route = Destinations.LecturasScreen.route + "/{password}",
        //  arguments = Destinations.LecturasScreen.arguments

        route = Destinations.DescargaFacturas.route + "/{cuenta}",
        arguments = Destinations.DescargaFacturas.arguments
    )
    {

        val cuenta = it.arguments?.getString("cuenta")
        val viewModel : DescargasFacturasViewModel = hiltViewModel()


        DescargaFacturasScreen(
            state = viewModel.state.value,
            cuenta = cuenta.toString(),
            onBack = { navController.popBackStack() },
            onListarFacturas = viewModel::listarFacturas,
            onDismissDialog = viewModel::hideErrorDialog,
            viewModel = viewModel
        )
    }
}


fun NavGraphBuilder.addOperacionesScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.Operaciones.route + "/{cuenta}",
        arguments = Destinations.Operaciones.arguments
    )
    {
        val cuenta = it.arguments?.getString("cuenta")

        OperacionesScreen(
            cuenta = cuenta.toString(),
            onClickEstado = { cuentaEstado ->
                navController.navigate(Destinations.EstadoCuenta.route + "/$cuentaEstado")
            },
            onClickPagos = { cuentaPagos ->
                navController.navigate(Destinations.Pagos.route + "/$cuentaPagos")
            },
            onClickDescarga = { cuentaDescarga ->
                navController.navigate(Destinations.DescargaFacturas.route + "/$cuentaDescarga")
            },
            onClickConsumo = { cuentaConsumo ->
                navController.navigate(Destinations.EvolucionConsumos.route + "/$cuentaConsumo")
            },
            onBack = { navController.popBackStack() },
        )
    }
}

fun NavGraphBuilder.addEstadoCuentaScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.EstadoCuenta.route + "/{cuenta}",
        arguments = Destinations.EstadoCuenta.arguments
    )
    {

        val cuenta = it.arguments?.getString("cuenta")
        val viewModel : EstadoCuentaViewModel = hiltViewModel()



        EstadoCuentaScreen(
            title = Destinations.EstadoCuenta.title,
            cuenta = cuenta.toString(),
            state = viewModel.state.value,
            onBack = { navController.popBackStack() },
            onDismissDialog = { viewModel.hideErrorDialog() },
            viewModel = viewModel
        )
    }
}


fun NavGraphBuilder.addEvolucionConsumosScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.EvolucionConsumos.route + "/{cuenta}",
        arguments = Destinations.EstadoCuenta.arguments
    ) {
        val cuenta = it.arguments?.getString("cuenta")
        val viewModel : EvolucionConsumosViewModel = hiltViewModel()


        EvolucionConsumosScreen(
            state = viewModel.state.value,
            title = Destinations.EvolucionConsumos.title,
            cuenta = cuenta.toString(),
            onBack = { navController.popBackStack() },
            viewModel = viewModel
        )
    }
}

fun NavGraphBuilder.addPagosScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.Pagos.route + "/{cuenta}",
        arguments = Destinations.Pagos.arguments
    ) {
        val cuenta = it.arguments?.getString("cuenta")

        PagosScreen(
            cuenta = cuenta.toString(),
            title = "Pagos",
            onBack = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.addPerfilScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.Perfil.route

    ) {

        val viewModel : PerfilViewModel = hiltViewModel()

        PerfilScreen(
            onBack = {
                navController.popBackStack(Destinations.Perfil.route, true)
                navController.navigate(Destinations.Main.route)
            },
            onChangePassword = { usuarioId ->
                navController.popBackStack(Destinations.Perfil.route, true)
                navController.navigate(Destinations.ChangePassword.route + "/$usuarioId")
            },
            viewModel = viewModel,
        )

    }
}


fun NavGraphBuilder.addReclamoScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.Reclamo.route

    ) {

        val viewModel : ReclamosViewmodel = hiltViewModel()

        ReclamoScreen(
            onBack = { navController.popBackStack() },
            onLlamarAOficina = viewModel::llamarXReclamo,
            onLlamarAGuardia = viewModel::llamarXReclamo,
            onSendMensaje = viewModel::enviarSmsWhatsApp,
        )
    }
}


fun NavGraphBuilder.addAreaConcesionScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.AreaConcesion.route

    ) {

        AreaConcesionScreen(
            onBack = { navController.popBackStack() },

            )
    }
}

fun NavGraphBuilder.addSeguridadElectricaScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.SeguridadELectrica.route

    ) {

        SeguridadElectricaScreen(
            onBack = { navController.popBackStack() },
        )
    }
}

fun NavGraphBuilder.addSimuladorConsumoScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.SimuladorConsumos.route

    ) {

        val viewModel : SimuladorConsumoViewModel = hiltViewModel()

        SimuladorConsumoScreen(
            state = viewModel.state.value,
            onBack = { navController.popBackStack() },
            onClick = { navController.navigate(Destinations.CategoriasElementosElectricos.route) },
            viewModel = viewModel
        )
    }
}


fun NavGraphBuilder.addCategoriasElementosElectricosScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.CategoriasElementosElectricos.route

    ) {
        val viewModel : CargarElementoElectricoViewModel = hiltViewModel()
        val state = viewModel.state

        CategoriasElementosElectricosScreen(
            onBack = { navController.popBackStack() },
            onClick = { categoria ->
                navController.navigate(Destinations.CargerElementos.route + "/$categoria")
            },
            viewModel = viewModel,
            state = state.value,
            onClickResultado = { navController.navigate(Destinations.Resultados.route) }
        )
    }
}


fun NavGraphBuilder.addCargarElementosScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.CargerElementos.route + "/{categoria}",
    ) {
        val categoria = it.arguments?.getString("categoria")
        val viewModel : CargarElementoElectricoViewModel = hiltViewModel()
        val state = viewModel.state
        state.value = state.value.copy(categoria = categoria.toString())


        CargarElementosScreen(
            state = state.value,
            onBack = { navController.popBackStack() },
            viewModel = viewModel
        )

    }
}

fun NavGraphBuilder.addResultadosScreen(
    navController : NavHostController
) {
    composable(
        route = Destinations.Resultados.route
    ) {

        val viewModel : CargarElementoElectricoViewModel = hiltViewModel()
        val state = viewModel.state

        ResultadosScreen(
            viewModel = viewModel,
            state = state.value,
            onBack = { navController.popBackStack() }
        )

    }
}


fun NavGraphBuilder.addPoliticaPrivacidadScreen(navController : NavHostController) {
    composable(
        route = Destinations.PoliticaPrivacidad.route
    ) {

        PoliticaPrivacidadSreen(
            onBack = { navController.popBackStack() }
        )

    }
}
