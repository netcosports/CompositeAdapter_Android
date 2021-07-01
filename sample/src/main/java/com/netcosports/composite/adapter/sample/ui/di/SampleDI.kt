package com.netcosports.composite.adapter.sample.ui.di

import com.netcosports.composite.adapter.sample.ui.SampleApplication

class SampleDI {
    companion object {

        private lateinit var app: SampleApplication

        fun init(app: SampleApplication) {
            this.app = app
        }

        fun provideApp(): SampleApplication = app
    }
}