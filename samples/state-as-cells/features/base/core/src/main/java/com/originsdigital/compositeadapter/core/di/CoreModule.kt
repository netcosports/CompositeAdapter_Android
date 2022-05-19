package com.originsdigital.compositeadapter.core.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.context.GlobalContext
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModule
    get() = module {
        factory(BG_DISPATCHER_QUALIFIER) { Dispatchers.IO }
        factory(UI_DISPATCHER_QUALIFIER) { Dispatchers.Main }
        factory(DEFAULT_DISPATCHER_QUALIFIER) { Dispatchers.Default }
    }

val UI_DISPATCHER_QUALIFIER: StringQualifier get() = named("UI_DISPATCHER")
val BG_DISPATCHER_QUALIFIER: StringQualifier get() = named("BG_DISPATCHER")
val DEFAULT_DISPATCHER_QUALIFIER: StringQualifier get() = named("DEFAULT_DISPATCHER")

val IS_DEBUG_QUALIFIER: StringQualifier get() = named("IS_DEBUG")
val IS_INTEGRATION_QUALIFIER: StringQualifier get() = named("IS_INTEGRATION")
val IS_PRODUCTION_BETA_QUALIFIER: StringQualifier get() = named("IS_PRODUCTION_BETA")
val IS_NOT_PRODUCTION_RELEASE_QUALIFIER: StringQualifier get() = named("IS_NOT_PRODUCTION_RELEASE")

val IS_DEBUG: Boolean get() = GlobalContext.get().get(IS_DEBUG_QUALIFIER)
val IS_INTEGRATION: Boolean get() = GlobalContext.get().get(IS_INTEGRATION_QUALIFIER)
val IS_PRODUCTION_BETA: Boolean get() = GlobalContext.get().get(IS_PRODUCTION_BETA_QUALIFIER)
val IS_NOT_PRODUCTION_RELEASE: Boolean
    get() = GlobalContext.get().get(IS_NOT_PRODUCTION_RELEASE_QUALIFIER)