package com.jlth.bodaplanaaq.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jlth.bodaplanaaq.presentation.ListaClientes
import com.jlth.bodaplanaaq.presentation.NuevoClienteScreen
import com.jlth.bodaplanaaq.screens.CuentaScreen
import com.jlth.bodaplanaaq.screens.ProductoScreen

@Composable
fun ClientesNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.name,
    ) {
        composable(NavScreen.HomeScreen.name) {
            ListaClientes(
                modifier = Modifier,
                navController = navController
            )
        }
        composable(NavScreen.ProductoScreen.name) {
            ProductoScreen()
        }
        composable(NavScreen.CuentaScreen.name) {
            CuentaScreen()
        }
        composable(NavScreen.NuevoClienteScreen.name) {
            NuevoClienteScreen(
                navController = navController
            )
        }
    }
}