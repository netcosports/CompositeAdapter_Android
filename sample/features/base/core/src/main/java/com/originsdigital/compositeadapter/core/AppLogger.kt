package com.originsdigital.compositeadapter.core

import org.koin.core.context.GlobalContext

interface AppLogger {

    fun logD(tag: String, message: String)

    fun logD(tag: String, message: String, error: Throwable)

    fun trackNonFatal(tag: String, message: String)
}

fun log(tag: Any, message: String) {
    log(getTag(tag), message)
}

fun log(tag: String, message: String) {
    GlobalContext.get().get<AppLogger>().logD(tag, message)
}

fun log(tag: Any, message: String, error: Throwable) {
    log(getTag(tag), message)
}

fun log(tag: String, message: String, error: Throwable) {
    GlobalContext.get().get<AppLogger>().logD(tag, message)
}

fun trackNonFatal(tag: Any, message: String) {
    trackNonFatal(getTag(tag), message)
}

fun trackNonFatal(tag: String, message: String) {
    GlobalContext.get().get<AppLogger>().trackNonFatal(tag, message)
}

private fun getTag(tag: Any): String {
    return "${tag::class.java.simpleName}@${tag.hashCode()}"
}