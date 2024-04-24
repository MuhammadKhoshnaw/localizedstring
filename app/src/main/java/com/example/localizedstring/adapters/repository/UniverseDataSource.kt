package com.example.localizedstring.adapters.repository

interface UniverseDataSource {
    suspend fun forgeANewUniverse(): UniverseDestinyResultRemote
    suspend fun resetExistence(): UniverseDestinyResultRemote
}
