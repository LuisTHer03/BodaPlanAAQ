package com.jlth.bodaplanaaq.di

import android.content.Context
import androidx.room.Room
import com.jlth.bodaplanaaq.core.Constants.Companion.BODA_TABLE
import com.jlth.bodaplanaaq.data.network.ClienteDB
import com.jlth.bodaplanaaq.data.network.ClienteDao
import com.jlth.bodaplanaaq.data.repository.ClienteRepositoryImpl
import com.jlth.bodaplanaaq.domain.repository.ClienteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideClienteDB(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ClienteDB::class.java,
        BODA_TABLE
    ).build()

    @Provides
    fun provideClienteDao (clienteDB: ClienteDB) = clienteDB.clienteDao()

    @Provides
    fun provideClienteRepositoryImpl(clienteDao: ClienteDao) : ClienteRepository =
        ClienteRepositoryImpl(clienteDao)

}