package com.woffka.adapter.sample.ui

import android.app.Application

class AdapterSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DI.init(this)
    }
}