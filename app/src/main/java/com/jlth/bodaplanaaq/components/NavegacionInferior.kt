package com.jlth.bodaplanaaq.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.jlth.bodaplanaaq.models.Items_bottom_nav.*
import com.jlth.bodaplanaaq.navigation.rutaActual

@Composable
fun NavegacionInferior(
    navController : NavHostController
) {
   val items = listOf(
       Item_bottom_nav1,
       Item_bottom_nav2,
       Item_bottom_nav3,
   ) 
    BottomAppBar {
        NavigationBar {
            items.forEach {items ->
                NavigationBarItem(
                    selected = rutaActual(navController = navController) == items.ruta, 
                    onClick = { navController.navigate(items.ruta) }, 
                    icon = { 
                        Icon(
                            imageVector = items.icon, 
                            contentDescription = items.title,
                        )
                    },
                    label = {
                        Text(text = items.title)
                    }
                )
            }
        }
    }
}