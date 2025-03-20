package com.jlth.bodaplanaaq.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jlth.bodaplanaaq.R
import com.jlth.bodaplanaaq.domain.model.Clientes
import com.jlth.bodaplanaaq.navigation.NavScreen
import com.jlth.bodaplanaaq.presentation.util.fondoDegradado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaClientes(
    viewModel: ClientesViewModel = hiltViewModel(),
    navController: NavHostController,
    modifier: Modifier,
) {
    //val clientes by viewModel.clientes.collectAsState()
    val clientes by viewModel.clientes.collectAsState(initial = emptyList())
    val context = LocalContext.current // Obtén el contexto aquí
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fondoDegradado(),
        verticalArrangement = Arrangement.Top
    ) {
        //Spacer(modifier = Modifier.height(5.dp))
        TopAppBar(title = { Text(text = "Mis Clientes") })
        Button(onClick = {
            navController.navigate(
                NavScreen.NuevoClienteScreen.name,
            ) },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Add, "Crear Cliente",)
            Text(text = "Crear cliente")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(clientes) { index, cliente ->
                ClienteItem(cliente = cliente,
                    onFavoritoClick = { viewModel.toogleFavorito(cliente) },
                    onLike = { viewModel.darLike(cliente) },
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
        border = BorderStroke(
            4.dp,
            Brush.linearGradient(
                colors = listOf(
                    Color.Cyan, Color.Magenta, Color.Blue
                )
            )
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.fondo2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
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
                    FavoritoButton(esFavorita = cliente.esFavorita) {
                        onFavoritoClick()
                    }
                    BadgedBox(
                        badge = {
                            if (cliente.likes >= 1) {
                                Badge { Text(text = cliente.likes.toString()) }
                            }
                        }
                    ) {
                        LikeButton(likes = cliente.likes) {
                            onLike()
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
}

@Composable
fun FavoritoButton(esFavorita: Boolean, onFavoritoClick: () -> Unit) {

    val scale = remember {
        Animatable(1f)
    }
    // sirve para ejecutar dos efectos secundarios al mismo tiempo, si no te utiliza tendremos que esperrar
    // a que termine la animacion de cada una
    LaunchedEffect(esFavorita ) {
        scale.animateTo(
            targetValue = 1.8f,
            animationSpec = tween(durationMillis = 200, easing = LinearEasing)
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 200, easing = LinearEasing)
        )
    }
    IconButton(onClick = onFavoritoClick) {
        Icon(
            if (esFavorita) Icons.Default.Favorite
            else Icons.Default.FavoriteBorder,
            contentDescription = "Favorito",
            modifier = Modifier
                .scale(scale.value)
        )
    }
}

@Composable
fun LikeButton(likes: Int, onLike: () -> Unit) {
    val rotation = remember {
        Animatable(360f)
    }
    LaunchedEffect(likes ) {
        rotation.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 700)
        )
        rotation.snapTo(targetValue = 360f)
    }
    IconButton(onClick = onLike ) {
        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = "Perfil",
            modifier = Modifier
                .rotate(rotation.value)
        )
    }
}