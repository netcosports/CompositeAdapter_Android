package com.originsdigital.compositeadapter.sample.ui.di

import com.originsdigital.compositeadapter.sample.ui.SampleApplication

class SampleDI {

    companion object {

        private lateinit var app: SampleApplication

        fun init(app: SampleApplication) {
            this.app = app
        }

        fun provideApp(): SampleApplication = app
    }
}