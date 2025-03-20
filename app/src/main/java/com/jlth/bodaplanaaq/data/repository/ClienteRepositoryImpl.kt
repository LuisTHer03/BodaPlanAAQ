package com.jlth.bodaplanaaq.data.repository

import com.jlth.bodaplanaaq.data.network.ClienteDao
import com.jlth.bodaplanaaq.domain.model.Clientes
import com.jlth.bodaplanaaq.domain.repository.ClienteRepository

class ClienteRepositoryImpl(
    private val clienteDao: ClienteDao
) : ClienteRepository {
    override fun getClientesFromRoom() = clienteDao.getClientes()
    override fun getClienteFromRoom(id: Int) = clienteDao.getCliente(id)
    override fun addClienteToRoom(nombre: Clientes) = clienteDao.addCliente(nombre)
    override fun deleteClienteFromRoom(nombre: Clientes) = clienteDao.deleteCliente(nombre)
    override fun updateClienteFromRoom(nombre: Clientes) = clienteDao.updateCliente(nombre)
}