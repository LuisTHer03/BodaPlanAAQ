package com.jlth.bodaplanaaq.data.network

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jlth.bodaplanaaq.core.Constants.Companion.BODA_TABLE
import com.jlth.bodaplanaaq.domain.model.Clientes
import com.jlth.bodaplanaaq.domain.repository.Clientess
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    @Query ("SELECT * FROM $BODA_TABLE ORDER BY id ASC")
    fun getClientes() : Flow<Clientess>

    @Query ("SELECT * FROM $BODA_TABLE WHERE id =:id")
    fun getCliente(id: Int) : Clientes

    @Insert
    fun addCliente(nombre : Clientes)

    @Delete
    fun deleteCliente(nombre: Clientes)

    @Update
    fun updateCliente(nombre: Clientes)
}