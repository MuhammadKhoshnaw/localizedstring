package com.example.localizedstring.usecase

import com.example.localizedstring.entity.UniverseDestinyResult

interface UniverseRepository {
    suspend fun forgeANewUniverse(): UniverseDestinyResult
    suspend fun resetExistence(): UniverseDestinyResult
}


