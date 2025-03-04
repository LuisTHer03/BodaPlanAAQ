package com.jlth.bodaplanaaq.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlth.bodaplanaaq.domain.model.Clientes

@Composable
fun ListaClientes(
    modifier: Modifier = Modifier,
    viewModel: ClientesViewModel = viewModel()
) {
    val clientes by viewModel.clientes.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Text(
            text = "Mis Clientes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(clientes) { index, cliente ->
                ClienteItem(cliente,
                    onFavoritoClick = { viewModel.toggleFavorito(index) },
                    onLike = { viewModel.darLike(index) },
                    onCompartir = { viewModel.compartirCliente(cliente.nombre, context)}
                )
            }
        }
    }
}

@Composable
fun ClienteItem(
    cliente: Clientes,
    onFavoritoClick: () -> Unit,
    onLike: () -> Unit,
    onCompartir: () -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = cliente.nombre,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onFavoritoClick) {
                    Icon(
                        if (cliente.esFavorita) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito",
                        //tint = favoritoColor
                    )
                }
                BadgedBox(
                    badge = {
                        Badge { Text(text = cliente.likes.toString())}
                    }
                ) {
                    IconButton(onClick = onLike ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Perfil"
                        )
                    }
                }
                IconButton(onClick = onCompartir ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Siguiente"
                    )
                }
            }
        }
    }
}