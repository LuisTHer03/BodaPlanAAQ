package com.jlth.bodaplanaaq.presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.jlth.bodaplanaaq.domain.model.Clientes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ClientesViewModel : ViewModel() {
    private val _clientes = MutableStateFlow<List<Clientes>>(listOf(
        Clientes(1, "José Luis", false, 0),
        Clientes(2, "Fernando", false, 0),
    ))

    // Solo mostrar y no modicar. solo exponer los datos
    val clientes: StateFlow<List<Clientes>> = _clientes

    // es una funcion para agregar o quitar una variable, en este caso esFavorita
    fun toggleFavorito(index: Int) {
        _clientes.value = _clientes.value.toMutableList().apply {
            this[index] = this[index].copy(esFavorita = !this[index].esFavorita)
        }
    }

    // Para sumar los likes
    fun darLike(index: Int) {
        _clientes.value = _clientes.value.toMutableList().apply {
            this[index] = this[index].copy(likes = this[index].likes+1)
        }
    }

    // Para compartir
    fun compartirCliente(nombre: String, context: Context) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, nombre)
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(sendIntent, null))
    }
}