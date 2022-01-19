package com.originsdigital.compositeadapter.sample.basic.ui

import android.app.Application
import com.originsdigital.compositeadapter.stateascells.app.StateAsCellsDI

class SamplesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        StateAsCellsDI.init(this)
    }
}