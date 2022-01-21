package com.originsdigital.compositeadapter.stateascells.app

import android.app.Application
import com.originsdigital.compositeadapter.BuildConfig
import com.originsdigital.compositeadapter.core.AppLogger
import com.originsdigital.compositeadapter.core.di.IS_DEBUG_QUALIFIER
import com.originsdigital.compositeadapter.core.di.IS_INTEGRATION_QUALIFIER
import com.originsdigital.compositeadapter.core.di.IS_NOT_PRODUCTION_RELEASE
import com.originsdigital.compositeadapter.core.di.IS_NOT_PRODUCTION_RELEASE_QUALIFIER
import com.originsdigital.compositeadapter.core.di.IS_PRODUCTION_BETA_QUALIFIER
import com.originsdigital.compositeadapter.core.di.coreModule
import com.originsdigital.compositeadapter.stateascells.home.ui.di.homeUIModule
import com.originsdigital.compositeadapter.stories.data.di.homeDataModule
import com.originsdigital.compositeadapter.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module


class StateAsCellsDI {

    companion object {
        fun init(application: Application) {
            startKoin {
                androidContext(application)
                modules(getModules())
            }
        }

        private fun getModules(): List<Module> {
            return listOf(
                coreModule,
                uiModule,
                homeDataModule(),
                homeUIModule(),
                appModule()
            )
        }

        private fun appModule(): Module {
            return module {
                single(IS_DEBUG_QUALIFIER) {
                    "debug".equals(BuildConfig.BUILD_TYPE, true)
                }
                single(IS_INTEGRATION_QUALIFIER) {
//                "integration".equals(BuildConfig.FLAVOR, true)
                    true
                }
                single(IS_PRODUCTION_BETA_QUALIFIER) {
                    /*"production".equals(BuildConfig.FLAVOR, true)
                            && */"beta".equals(BuildConfig.BUILD_TYPE, true)
                }
                single(IS_NOT_PRODUCTION_RELEASE_QUALIFIER) {
                    BuildConfig.DEBUG
//                        || !"production".equals(BuildConfig.FLAVOR, true)
                            || !"release".equals(BuildConfig.BUILD_TYPE, true)
                }
                single<AppLogger> { AppLoggerImpl(IS_NOT_PRODUCTION_RELEASE) }
            }
        }
    }
}