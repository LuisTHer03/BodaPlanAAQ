package com.jlth.bodaplanaaq.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jlth.bodaplanaaq.core.Constants.Companion.BODA_TABLE

@Entity (tableName = BODA_TABLE)
data class Clientes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val esFavorita: Boolean,
    val likes: Int,
)
