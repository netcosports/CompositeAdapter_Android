package com.originsdigital.compositeadapter.messages.ui

import android.app.Application

class SampleDI {

    companion object {

        private lateinit var app: Application

        fun init(app: Application) {
            Companion.app = app
        }

        fun provideApp(): Application = app
    }
}