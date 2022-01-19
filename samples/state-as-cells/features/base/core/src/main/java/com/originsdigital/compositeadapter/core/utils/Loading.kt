package com.originsdigital.compositeadapter.core.utils

import com.originsdigital.compositeadapter.core.extensions.rethrowIfNeeded
import com.originsdigital.compositeadapter.core.log

suspend inline fun <T> safeLoad(
    tag: Any,
    text: String,
    crossinline block: (suspend () -> T),
    crossinline error: (suspend (Exception) -> T)
): T {
    return try {
        log(tag, "$text started")
        block().also {
            log(tag, "$text result = $it")
        }
    } catch (e: Exception) {
        e.rethrowIfNeeded()
        log(tag, "$text error =", e)
        error(e)
    }
}