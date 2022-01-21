package com.originsdigital.compositeadapter.stateascells.home.ui.di

import com.originsdigital.compositeadapter.stateascells.home.ui.HomeUIMapper
import com.originsdigital.compositeadapter.stateascells.home.ui.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun homeUIModule(): Module {
    return module {
        factory {
            HomeUIMapper(
                app = androidApplication(),
                sceneMapper = get()
            )
        }
        viewModel {
            HomeViewModel(
                homeRepository = get(),
                homeUIMapper = get()
            )
        }
    }
}