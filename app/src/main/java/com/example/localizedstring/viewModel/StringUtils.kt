package com.example.localizedstring.viewModel

import android.annotation.SuppressLint
import android.content.Context
import com.example.localizedstring.entity.empty
import timber.log.Timber

fun Context.string(id: String?, vararg formatArgs: Any) = runOrEmpty {
    if (id.isNullOrEmpty()) String.empty
    else lokaliseString(id, *formatArgs).orEmpty()
}

private fun Context.lokaliseString(
    key: String,
    vararg formatArgs: Any,
): String? = runCatching {
    getStringResourceByName(key, *formatArgs) ?: thirdPartyLokalise(key, *formatArgs)
}.getOrElse {
    Timber.e(it)
    getStringResourceByName(key, *formatArgs)
}

private const val STRING_RESOURCE_TYPE = "string"

@SuppressLint("DiscouragedApi")
private fun Context.getStringResourceByName(
    aString: String,
    vararg formatArgs: Any,
): String? = runCatching {
    val packageName: String = packageName
    val resId: Int = resources.getIdentifier(aString, STRING_RESOURCE_TYPE, packageName)
    getString(resId, *formatArgs)
}.getOrElse {
    Timber.e(it)
    null
}

private fun thirdPartyLokalise(key: String, vararg formatArgs: Any): String? {
    // use your third party localization library here
    return null
}
