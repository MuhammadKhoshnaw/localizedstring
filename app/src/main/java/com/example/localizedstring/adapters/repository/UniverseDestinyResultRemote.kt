package com.example.localizedstring.adapters.repository

data class UniverseDestinyResultRemote(
    val messageTranslationKey: String,
    val messageArgs: List<String>,
    val success: Boolean,
)
