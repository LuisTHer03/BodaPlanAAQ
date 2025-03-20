package com.jlth.bodaplanaaq.domain.repository

import com.jlth.bodaplanaaq.domain.model.Clientes
import kotlinx.coroutines.flow.Flow

typealias Clientess = List<Clientes>
interface ClienteRepository {
    fun getClientesFromRoom() : Flow<Clientess>
    fun getClienteFromRoom(id: Int) : Clientes
    fun addClienteToRoom(nombre: Clientes)
    fun deleteClienteFromRoom(nombre: Clientes)
    fun updateClienteFromRoom(nombre: Clientes)
}