package com.originsdigital.compositeadapter.ui.vm

import kotlinx.coroutines.Job

abstract class BaseDataViewModel : BaseViewModel() {

    open fun onRefresh() = loadData(force = false)

    protected abstract fun getData(force: Boolean): Job?

    protected open fun loadData(force: Boolean) {
        job?.cancel()
        job = getData(force)
    }

    protected fun firstLoad() = loadData(force = true)
}