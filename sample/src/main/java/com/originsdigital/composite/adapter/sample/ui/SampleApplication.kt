package com.originsdigital.composite.adapter.sample.ui

import android.app.Application
import com.originsdigital.composite.adapter.sample.ui.di.SampleDI

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SampleDI.init(this)
    }
}