package com.originsdigital.compositeadapter.stories.data.di

import com.originsdigital.compositeadapter.home.core.repository.HomeRepository
import com.originsdigital.compositeadapter.stories.data.repository.HomeRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun homeDataModule(): Module {
    return module {
        factory<HomeRepository> { HomeRepositoryImpl() }
    }
}