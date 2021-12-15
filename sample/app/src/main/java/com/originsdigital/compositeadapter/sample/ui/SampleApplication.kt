package com.originsdigital.compositeadapter.sample.ui

import android.app.Application
import com.originsdigital.compositeadapter.messages.ui.SampleDI

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SampleDI.init(this)
    }
}