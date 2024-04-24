package com.example.localizedstring.adapters.repository

import com.example.localizedstring.entity.UniverseDestinyResult
import com.example.localizedstring.usecase.UniverseRepository
import javax.inject.Inject

class UniverseRepositoryImp @Inject constructor(
    private val universeDataSource: UniverseDataSource
) : UniverseRepository {
    override suspend fun forgeANewUniverse(): UniverseDestinyResult =
        universeDataSource.forgeANewUniverse().toEntity()

    override suspend fun resetExistence(): UniverseDestinyResult =
        universeDataSource.resetExistence().toEntity()
}
