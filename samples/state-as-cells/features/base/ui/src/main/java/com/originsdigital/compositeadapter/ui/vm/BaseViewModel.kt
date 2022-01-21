package com.originsdigital.compositeadapter.ui.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.originsdigital.compositeadapter.core.di.BG_DISPATCHER_QUALIFIER
import com.originsdigital.compositeadapter.core.di.UI_DISPATCHER_QUALIFIER
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import org.koin.core.context.GlobalContext

abstract class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    protected val bgDispatcher: CoroutineDispatcher by lazy {
        GlobalContext.get().get(BG_DISPATCHER_QUALIFIER)
    }
    protected val uiDispatcher: CoroutineDispatcher by lazy {
        GlobalContext.get().get(UI_DISPATCHER_QUALIFIER)
    }

    protected var job: Job? = null

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}