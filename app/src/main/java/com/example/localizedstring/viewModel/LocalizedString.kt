package com.example.localizedstring.viewModel

import android.content.Context
import androidx.annotation.StringRes
import com.example.localizedstring.entity.empty

interface LocalizedString {
    val formatArgs: List<Any>
    fun string(context: Context): String
}

data class LocalizedIntIdString(
    @StringRes val id: Int,
    override val formatArgs: List<Any> = emptyList()
) : LocalizedString {
    override fun string(context: Context) = context.string(id, *formatArgs.toTypedArray())
}

data class LocalizedStringIdString(
    val id: String,
    override val formatArgs: List<Any> = emptyList()
) : LocalizedString {
    override fun string(context: Context) = context.string(id, *formatArgs.toTypedArray())
}

data class LocalizedStaticString(
    val string: String,
    override val formatArgs: List<Any> = emptyList()
) : LocalizedString {
    override fun string(context: Context) = string
}

data class LocalizedEmptyString(
    override val formatArgs: List<Any> = emptyList()
) : LocalizedString {
    override fun string(context: Context) = String.empty
}

fun localizedString(id: Int, vararg formatArgs: Any): LocalizedString =
    LocalizedIntIdString(id, formatArgs.toList())

fun localizedString(id: String, vararg formatArgs: Any): LocalizedString =
    LocalizedStringIdString(id, formatArgs.toList())

fun localizedStaticString(string: String): LocalizedString =
    LocalizedStaticString(string)

fun localizedString(): LocalizedString = LocalizedEmptyString()
