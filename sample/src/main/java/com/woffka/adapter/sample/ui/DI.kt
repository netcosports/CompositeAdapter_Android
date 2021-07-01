package com.woffka.adapter.sample.ui

class DI {
    companion object {

        private lateinit var app: AdapterSampleApplication

        fun init(app: AdapterSampleApplication) {
            this.app = app
        }

        fun provideApp(): AdapterSampleApplication = app
    }
}