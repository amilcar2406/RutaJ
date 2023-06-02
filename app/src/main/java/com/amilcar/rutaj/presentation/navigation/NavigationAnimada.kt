package com.amilcar.rutaj.presentation.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.amilcar.rutaj.presentation.login.LoginScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dagger.hilt.android.lifecycle.HiltViewModel
/*
@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController : NavHostController
) {

    BoxWithConstraints {

        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.LoginScreen.route)
        {
            addLoginAnimada(navController)
            addLecturasScreenAnimada(navController)
            addBusquedaScreenAnimada(navController)
        }

    }
}



@ExperimentalAnimationApi
fun NavGraphBuilder.addLoginAnimada(
    navController : NavHostController
){
    composable(
        Destinations.LoginScreen.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = {1000},
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popEnterTransition =  { _, _ ->
            slideInHorizontally(
                initialOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }
    ){
        val viewModel : LoginViewModel = hiltViewModel()

        if(viewModel.state.value.successLogin){
            LaunchedEffect(key1 = Unit){
                navController.navigate(
                    Destinations.LecturasScreen.route
                ){
                    popUpTo(Destinations.LoginScreen.route){
                       inclusive = true
                       viewModel.state.value = viewModel.state.value.copy(successLogin=false)
                    }
                }
            }
        } else {
            LoginScreen(
                state = viewModel.state.value,
                onLogin = viewModel::login,
                onDismissDialog = viewModel::hideErrorDialog
            )
        }
    }
}





@ExperimentalAnimationApi
fun NavGraphBuilder.addLecturasScreenAnimada(
    navController : NavHostController,
){
    composable(
        Destinations.LecturasScreen.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = {1000},
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popEnterTransition =  { _, _ ->
            slideInHorizontally(
                initialOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }
    ) {


        val viewModel : LecturasViewModel = hiltViewModel()

        LecturasScreen(
            navController,
            viewModel = viewModel
        )

    }
}


@ExperimentalAnimationApi
fun NavGraphBuilder.addBusquedaScreenAnimada(
    navController : NavHostController
){
    composable(
        Destinations.BuscarScreen.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = {1000},
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popEnterTransition =  { _, _ ->
            slideInHorizontally(
                initialOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }
    ){
         val viewModel : BuscarViewModel = hiltViewModel()

          BuscarScreen(
              navController,
              viewModel = viewModel
          )
    }*/

