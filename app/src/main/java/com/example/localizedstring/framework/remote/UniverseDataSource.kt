package com.example.localizedstring.framework.remote

import com.example.localizedstring.adapters.repository.UniverseDataSource
import com.example.localizedstring.adapters.repository.UniverseDestinyResultRemote
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

/**
 * normally this data source would get and upload data from a remote server
 * */
class UniverseDataSourceImp @Inject constructor() : UniverseDataSource {

    override suspend fun forgeANewUniverse(): UniverseDestinyResultRemote = generateDestiny(
        durationFrom = 5_000L,
        durationUntil = 10_000L,
        successMessageKey = "forge_a_new_universe_message_result_success",
        failedMessageKey = "forge_a_new_universe_message_result_failed"
    )

    override suspend fun resetExistence(): UniverseDestinyResultRemote = generateDestiny(
        durationFrom = 10_000L,
        durationUntil = 20_000L,
        successMessageKey = "reset_existence_message_result_success",
        failedMessageKey = "reset_existence_message_result_failed"
    )

    private suspend fun generateDestiny(
        durationFrom: Long,
        durationUntil: Long,
        successMessageKey: String,
        failedMessageKey: String,
    ): UniverseDestinyResultRemote {
        val duration = Random.nextLong(durationFrom, durationUntil)
        delay(duration)
        return generateDestiny(
            duration = duration,
            successMessageKey = successMessageKey,
            failedMessageKey = failedMessageKey
        )
    }

    private fun generateDestiny(
        duration: Long,
        successMessageKey: String,
        failedMessageKey: String
    ): UniverseDestinyResultRemote {
        val success = Random.nextBoolean()
        val (messageTranslationKey: String, messageArgs: List<String>) = generateMessage(
            success = success,
            duration = duration,
            successMessageKey = successMessageKey,
            failedMessageKey = failedMessageKey
        )
        return UniverseDestinyResultRemote(
            messageTranslationKey = messageTranslationKey,
            messageArgs = messageArgs,
            success = success
        )
    }

    private fun generateMessage(
        success: Boolean,
        duration: Long,
        successMessageKey: String,
        failedMessageKey: String,
    ): Pair<String, List<String>> = if (success) Pair(
        successMessageKey,
        listOf(duration.toString())
    ) else Pair(
        failedMessageKey,
        emptyList()
    )

}
