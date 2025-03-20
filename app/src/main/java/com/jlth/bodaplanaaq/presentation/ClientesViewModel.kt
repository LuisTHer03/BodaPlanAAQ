package com.jlth.bodaplanaaq.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlth.bodaplanaaq.domain.model.Clientes
import com.jlth.bodaplanaaq.domain.repository.ClienteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientesViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {
        /*private val _clientes = MutableStateFlow<List<Clientes>>(listOf(
        Clientes(1, "José Luis", false, 0),
        Clientes(2, "Fernando", false, 0),
        Clientes(3, "Pedro", false, 0),
    ))

    // Solo mostrar y no modicar. solo exponer los datos
    val clientes: StateFlow<List<Clientes>> = _clientes*/

    val clientes = clienteRepository.getClientesFromRoom()
    val cliente by mutableStateOf(Clientes(0,"", false,0))

/*    // es una funcion para agregar o quitar una variable, en este caso esFavorita
    fun toggleFavorito(index: Int) {
        _clientes.value = _clientes.value.toMutableList().apply {
            this[index] = this[index].copy(esFavorita = !this[index].esFavorita)
        }
    }*/

    fun toogleFavorito(clientes: Clientes) = viewModelScope.launch (Dispatchers.IO){
        val nuevoCliente = cliente.copy(esFavorita = !cliente.esFavorita)
        clienteRepository.updateClienteFromRoom(nuevoCliente)
    }

    /*// Para sumar los likes
    fun darLike(index: Int) {
        _clientes.value = _clientes.value.toMutableList().apply {
            this[index] = this[index].copy(likes = this[index].likes+1)
        }
    }*/

    fun darLike(clientes: Clientes) = viewModelScope.launch(Dispatchers.IO) {
        val nuevoCliente = cliente.copy(likes = cliente.likes + 1)
        clienteRepository.updateClienteFromRoom(nuevoCliente)
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

    fun addCliente(clientes: Clientes) = viewModelScope.launch(Dispatchers.IO) {
        clienteRepository.addClienteToRoom(clientes)
    }

    fun updateCliente(clientes: Clientes) = viewModelScope.launch(Dispatchers.IO) {
        clienteRepository.updateClienteFromRoom(clientes)
    }
}
