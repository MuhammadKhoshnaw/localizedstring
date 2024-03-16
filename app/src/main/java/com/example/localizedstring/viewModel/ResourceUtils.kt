package com.example.localizedstring.viewModel

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes

fun Context.string(@StringRes id: Int) = runOrEmpty {
    resources.string(id)
}

fun Context.string(@StringRes id: Int, vararg formatArgs: Any) = runOrEmpty {
    resources.string(id, *formatArgs)
}

fun Resources.string(@StringRes id: Int): String = runOrEmpty {
    getString(id)
}

fun Resources.string(@StringRes id: Int, vararg formatArgs: Any): String = runOrEmpty {
    getString(id, *formatArgs)
}
