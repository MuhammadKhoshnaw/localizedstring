package com.example.localizedstring.adapters.repository

import com.example.localizedstring.entity.UniverseDestinyResult
import com.example.localizedstring.entity.localizedString

fun UniverseDestinyResultRemote.toEntity() = UniverseDestinyResult(
    message = localizedString(id = messageTranslationKey, args = messageArgs),
    success = success,
)
