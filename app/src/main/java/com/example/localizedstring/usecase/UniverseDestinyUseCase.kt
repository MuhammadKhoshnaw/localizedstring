package com.example.localizedstring.usecase

import javax.inject.Inject

class UniverseDestinyUseCase @Inject constructor(
    private val universeRepository: UniverseRepository
) {

    suspend fun forgeANewUniverse() = universeRepository.forgeANewUniverse()

    suspend fun resetExistence() = universeRepository.resetExistence()
}
