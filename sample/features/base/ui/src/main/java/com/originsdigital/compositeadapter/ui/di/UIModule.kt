package com.originsdigital.compositeadapter.ui.di

import com.originsdigital.compositeadapter.ui.mapper.ErrorUIMapper
import com.originsdigital.compositeadapter.ui.mapper.LoaderUIMapper
import com.originsdigital.compositeadapter.ui.mapper.SceneMapper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val uiModule
    get() = module {
        factory { ErrorUIMapper() }
        factory { LoaderUIMapper() }
        factory {
            SceneMapper(
                app = androidApplication(),
                loaderUIMapper = get(),
                errorUIMapper = get()
            )
        }
    }