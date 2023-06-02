package com.amilcar.rutaj.presentation.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.amilcar.rutaj.presentation.navigation.Destinations
import com.amilcar.rutaj.presentation.navigation.currentRow


@Composable
fun BottomNavigationBar(
    navController : NavHostController,
    items : List<Destinations>
) {

   // val viewModel : TurnosViewmodel = hiltViewModel()
    val currentRow = currentRow(navController)



    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                alwaysShowLabel = true,
                selectedContentColor = MaterialTheme.colors.secondaryVariant,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                selected = currentRow  == screen.route,
                onClick = {

                    when (screen.title) {
                        "Operaciones" -> {
                            navController.navigate(screen.route)
                        }
                        "Reclamo" -> {
                            navController.navigate(screen.route)
                        }
                        else -> {
                           //nada
                        }
                    }
                }
            )
        }
    }
}
