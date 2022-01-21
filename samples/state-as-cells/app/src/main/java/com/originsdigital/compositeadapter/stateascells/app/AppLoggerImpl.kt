package com.originsdigital.compositeadapter.stateascells.app

import android.util.Log
import com.originsdigital.compositeadapter.core.AppLogger

class AppLoggerImpl(private val isEnabled: Boolean) : AppLogger {

    override fun logD(tag: String, message: String) {
        if (isEnabled) {
            Log.d(tag, message)
        }
    }

    override fun logD(tag: String, message: String, error: Throwable) {
        if (isEnabled) {
            Log.d(tag, "$message $error")
            error.printStackTrace()
        }
    }

    override fun trackNonFatal(tag: String, message: String) {
        try {
            throw IllegalStateException("$tag $message")
        } catch (stackTrace: IllegalStateException) {
//            FirebaseCrashlytics.getInstance().recordException(stackTrace)
            if (isEnabled) {
                throw stackTrace
            }
        }
    }
}