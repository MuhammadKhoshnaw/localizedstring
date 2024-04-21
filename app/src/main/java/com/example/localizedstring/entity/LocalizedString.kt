package com.example.localizedstring.entity

import java.io.Serializable

sealed interface LocalizedString : Serializable

data class LocalizedIntId(
    val id: Int,
    val args: List<Any> = emptyList()
) : LocalizedString

data class LocalizedStringId(
    val id: String,
    val args: List<Any> = emptyList()
) : LocalizedString

data class LocalizedRawString(
    val string: String
) : LocalizedString

object LocalizedEmptyString : LocalizedString
