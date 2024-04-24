package com.example.localizedstring.framework.di

import com.example.localizedstring.adapters.repository.UniverseRepositoryImp
import com.example.localizedstring.usecase.UniverseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUniverseRepository(universeRepositoryImpl: UniverseRepositoryImp): UniverseRepository
}
