@file:Suppress("UnusedReceiverParameter")

package com.example.localizedstring.viewModel

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import com.example.localizedstring.entity.LocalizedEmptyString
import com.example.localizedstring.entity.LocalizedIntId
import com.example.localizedstring.entity.LocalizedRawString
import com.example.localizedstring.entity.LocalizedString
import com.example.localizedstring.entity.LocalizedStringId
import com.example.localizedstring.entity.empty

@Composable
fun LocalizedIntId.string(): String = string(LocalContext.current)

fun LocalizedIntId.string(context: Context): String {
    val translatedArgs = args.map { arg ->
        if (arg is LocalizedString) arg.string(context)
        else arg
    }
    return context.string(id, *translatedArgs.toTypedArray())
}

@Composable
fun LocalizedStringId.string(): String = string(LocalContext.current)

// TODO: User your third party library to get the string if you have one
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
fun LocalizedStringId.string(context: Context): String = "String id note implemented yet!"

fun LocalizedRawString.string(): String = string

fun LocalizedEmptyString.string(): String = String.empty

@Composable
fun LocalizedString.string(): String = string(LocalContext.current)

fun Fragment.string(
    localizedString: LocalizedString
): String = localizedString.string(requireContext())

fun Activity.string(
    localizedString: LocalizedString
): String = localizedString.string(this)

fun LocalizedString.string(context: Context): String = when (this) {
    is LocalizedIntId -> string(context)
    is LocalizedStringId -> string(context)
    is LocalizedRawString -> string()
    is LocalizedEmptyString -> string()
}

@Composable
fun List<LocalizedString>.string(): List<String> = map { it.string() }
