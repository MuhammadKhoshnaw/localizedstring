package com.example.localizedstring.framework.di

import com.example.localizedstring.adapters.repository.UniverseDataSource
import com.example.localizedstring.framework.remote.UniverseDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindUniverseDataSource(universeDataSourceImp: UniverseDataSourceImp): UniverseDataSource

}
