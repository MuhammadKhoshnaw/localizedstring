@file:Suppress("unused")

package com.example.localizedstring.entity

fun localizedString(
    id: Int,
    vararg args: Any
): LocalizedString = LocalizedIntId(id = id, args.toList())

fun localizedString(
    id: Int,
    args: List<Any>,
): LocalizedString = LocalizedIntId(id = id, args.toList())

fun localizedString(
    id: String,
    vararg args: Any
): LocalizedString = LocalizedStringId(id = id, args.toList())

fun localizedString(
    id: String,
    args: List<Any>
): LocalizedString = LocalizedStringId(id = id, args.toList())

fun localizedRowString(
    string: String
): LocalizedString = LocalizedRawString(string = string)

fun localizedString(): LocalizedString = LocalizedEmptyString

fun Int.toLocalizedString(
    vararg args: Any
): LocalizedString = LocalizedIntId(id = this, args = args.toList())

fun String.toLocalizedString(
    vararg args: Any
): LocalizedString = LocalizedStringId(id = this, args = args.toList())

fun String.toLocalizedRowString(): LocalizedString = localizedRowString(string = this)

fun LocalizedString.withArgs(
    vararg args: Any
): LocalizedString = when (this) {
    is LocalizedIntId -> LocalizedIntId(id = id, args = args.toList())
    is LocalizedStringId -> LocalizedStringId(id = id, args = args.toList())
    else -> this
}
