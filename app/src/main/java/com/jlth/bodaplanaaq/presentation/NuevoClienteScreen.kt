package com.jlth.bodaplanaaq.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jlth.bodaplanaaq.domain.model.Clientes
import com.jlth.bodaplanaaq.presentation.util.bordeDegradado
import com.jlth.bodaplanaaq.presentation.util.fondoDegradado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoClienteScreen(
    navController: NavHostController,
    viewModel: ClientesViewModel = hiltViewModel()
) {
    var textoCliente by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fondoDegradado()
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigateUp()}
                ) {
                    Icon(Icons.Default.ArrowBackIosNew, "Ir atrás")
                }
            },
            title = { Text(text = "Nuevo Cliente") }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = textoCliente,
                onValueChange = { textoCliente = it},
                label = { Text(text = "Escriba el cliente") },
                modifier = Modifier
                    .fillMaxWidth()
                    .bordeDegradado(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (textoCliente.isNotBlank()) {
                        val nuevoCliente = Clientes(id = 0, nombre = textoCliente,
                            esFavorita = false, likes = 0
                        )
                        viewModel.addCliente(nuevoCliente)
                        navController.navigateUp()
                    }
                }
            ) {
                Text(text = "Guardar cliente")
            }
        }
    }
}