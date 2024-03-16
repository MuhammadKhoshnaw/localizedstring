package com.example.localizedstring.ui.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.example.localizedstring.viewModel.LocalizedString
import com.example.localizedstring.viewModel.string

@Composable
@ReadOnlyComposable
fun string(
    @StringRes id: Int
): String = LocalContext.current.string(id)

@Composable
@ReadOnlyComposable
fun string(
    @StringRes id: Int,
    vararg formatArgs: Any
): String = LocalContext.current.string(id, *formatArgs)

@Composable
@ReadOnlyComposable
fun LocalizedString.string(): String = this.string(LocalContext.current)

@Composable
@ReadOnlyComposable
fun List<LocalizedString>.string(): List<String> = map { it.string() }
