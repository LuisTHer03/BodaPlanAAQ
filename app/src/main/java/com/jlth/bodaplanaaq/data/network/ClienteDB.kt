package com.jlth.bodaplanaaq.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jlth.bodaplanaaq.domain.model.Clientes

@Database (entities = [Clientes::class], version = 1, exportSchema = false)
abstract class ClienteDB : RoomDatabase() {
    abstract fun clienteDao() : ClienteDao
}