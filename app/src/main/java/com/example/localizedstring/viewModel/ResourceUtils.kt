package com.example.localizedstring.viewModel

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

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
