package com.example.localizedstring.adapters.viewModel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.localizedstring.entity.empty
import timber.log.Timber

fun Context.string(@StringRes id: Int) = runOrEmpty {
    resources.string(id)
}

fun Context.string(@StringRes id: Int, vararg formatArgs: Any) = runOrEmpty {
    resources.string(id, *formatArgs)
}

fun Fragment.string(@StringRes id: Int) = runOrEmpty {
    requireContext().string(id)
}

fun Fragment.string(@StringRes id: Int, vararg formatArgs: Any) = runOrEmpty {
    requireContext().string(id, *formatArgs)
}

fun Activity.string(@StringRes id: Int) = runOrEmpty {
    this.resources.string(id)
}

fun Activity.string(@StringRes id: Int, vararg formatArgs: Any) = runOrEmpty {
    this.resources.string(id, *formatArgs)
}

fun Resources.string(@StringRes id: Int): String = runOrEmpty {
    getString(id)
}

fun Resources.string(@StringRes id: Int, vararg formatArgs: Any): String = runOrEmpty {
    getString(id, *formatArgs)
}


private const val STRING_RESOURCE_TYPE = "string"

@SuppressLint("DiscouragedApi")
fun Context.getStringResourceByName(
    aString: String,
    vararg formatArgs: Any,
): String = runCatching {
    val packageName: String = packageName
    val resId: Int = resources.getIdentifier(aString, STRING_RESOURCE_TYPE, packageName)
    getString(resId, *formatArgs)
}.getOrElse {
    Timber.tag("getStringResourceByName").e(it)
    String.empty
}
