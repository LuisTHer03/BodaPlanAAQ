package com.jlth.bodaplanaaq.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.jlth.bodaplanaaq.navigation.NavScreen

sealed class Items_bottom_nav(
    val icon: ImageVector,
    val title: String,
    val ruta: String,
) {
    object Item_bottom_nav1 : Items_bottom_nav(
        Icons.Filled.Home,
        title = "Inicio",
        NavScreen.HomeScreen.name,
    )
    object Item_bottom_nav2 : Items_bottom_nav(
        Icons.Filled.ShoppingCart,
        title = "Productos",
        NavScreen.ProductoScreen.name,
    )
    object Item_bottom_nav3 : Items_bottom_nav(
        Icons.Filled.AccountCircle,
        title = "Cuenta",
        NavScreen.CuentaScreen.name,
    )
}