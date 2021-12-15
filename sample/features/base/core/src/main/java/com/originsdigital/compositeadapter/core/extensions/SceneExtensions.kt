package com.originsdigital.compositeadapter.core.extensions

import com.originsdigital.compositeadapter.core.entity.Scene

val <DATA> Scene<DATA>.dataOrNull: DATA?
    get() {
        return when (this) {
            is Scene.Loading, is Scene.Error -> null
            is Scene.Data -> this.data
        }
    }

val <DATA> Scene<DATA>.isEmpty: Boolean get() = dataOrNull.isEmpty

val Any?.isEmpty: Boolean
    get() {
        return when (this) {
            null -> true
            is Collection<*> -> isEmpty()
            else -> false
        }
    }

fun <DATA> Scene<DATA>.toLoading(force: Boolean): Scene<DATA> {
    return when (this) {
        is Scene.Loading -> this
        is Scene.Error -> {
            if (force) {
                Scene.Loading()
            } else {
                this.copy(isRefreshing = true)
            }
        }
        is Scene.Data -> {
            if (force) {
                Scene.Loading()
            } else {
                this.copy(isRefreshing = true)
            }
        }
    }
}

fun <DATA> Scene<DATA>.toError(
    error: Throwable,
    force: Boolean
): Scene<DATA> {
    return when (this) {
        is Scene.Loading, is Scene.Error -> Scene.Error(throwable = error, isRefreshing = false)
        is Scene.Data -> if (force) {
            Scene.Error(throwable = error, isRefreshing = false)
        } else {
            this.copy(isRefreshing = false)
        }
    }
}

fun <DATA> Scene<DATA>.toData(data: DATA): Scene<DATA> {
    return when (this) {
        is Scene.Loading, is Scene.Error -> Scene.Data(data = data, isRefreshing = false)
        is Scene.Data -> this.copy(data = data, isRefreshing = false)
    }
}

fun <DATA, NEW_DATA> Scene.Loading<DATA>.mapData(): Scene<NEW_DATA> {
    return Scene.Loading()
}

fun <DATA, NEW_DATA> Scene.Error<DATA>.mapData(): Scene<NEW_DATA> {
    return Scene.Error(throwable = throwable, isRefreshing = isRefreshing)
}

fun <DATA, NEW_DATA> Scene.Data<DATA>.mapData(
    mapper: (DATA) -> NEW_DATA
): Scene<NEW_DATA> {
    return Scene.Data(data = mapper(data), isRefreshing = isRefreshing)
}

fun <DATA, NEW_DATA> Scene<DATA>.mapData(
    mapper: (DATA) -> NEW_DATA
): Scene<NEW_DATA> {
    return when (this) {
        is Scene.Loading -> this.mapData()
        is Scene.Error -> this.mapData()
        is Scene.Data -> this.mapData(mapper = mapper)
    }
}

fun <DATA, NEW_DATA> Scene<DATA>.mapDataScene(
    mapper: (Scene.Data<DATA>) -> Scene<NEW_DATA>
): Scene<NEW_DATA> {
    return when (this) {
        is Scene.Loading -> this.mapData()
        is Scene.Error -> this.mapData()
        is Scene.Data -> mapper(this)
    }
}