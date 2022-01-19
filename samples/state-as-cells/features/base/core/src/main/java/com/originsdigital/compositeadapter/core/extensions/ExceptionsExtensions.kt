package com.originsdigital.compositeadapter.core.extensions

import kotlinx.coroutines.TimeoutCancellationException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.nio.channels.ClosedChannelException
import kotlin.coroutines.cancellation.CancellationException

fun Throwable.rethrowIfNeeded() {
    if (this !is TimeoutCancellationException && this is CancellationException) {
        throw this
    }
}

val Throwable.isInternetConnectionException: Boolean
    get() {
        return this is UnknownHostException
                || this is UnknownServiceException
                || this is InterruptedIOException
                || this is SocketException
                || this is ClosedChannelException
    }