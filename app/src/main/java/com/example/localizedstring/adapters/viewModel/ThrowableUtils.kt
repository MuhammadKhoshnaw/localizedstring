package com.example.localizedstring.adapters.viewModel

import com.example.localizedstring.entity.empty
import timber.log.Timber

fun runOrEmpty(block: () -> String) = runOrDefault(
    default = String.empty,
    block = block
)

fun <T> runOrDefault(default: T, block: () -> T) = runCatching {
    block()
}.onFailure {
    Timber.e(it)
}.getOrDefault(default)
