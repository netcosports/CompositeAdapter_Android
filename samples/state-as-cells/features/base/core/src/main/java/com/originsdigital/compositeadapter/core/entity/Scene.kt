package com.originsdigital.compositeadapter.core.entity

sealed class Scene<DATA> {

    abstract val isRefreshing: Boolean

    data class Loading<DATA>(
        override val isRefreshing: Boolean = false
    ) : Scene<DATA>()

    data class Error<DATA>(
        val throwable: Throwable,
        override val isRefreshing: Boolean
    ) : Scene<DATA>()

    data class Data<DATA>(
        val data: DATA,
        override val isRefreshing: Boolean
    ) : Scene<DATA>()
}