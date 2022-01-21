package com.originsdigital.compositeadapter.ui.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.originsdigital.compositeadapter.ui.vm.BaseStateViewModel
import com.originsdigital.compositeadapter.ui.vm.BaseViewModel

fun observeLifecycle(
    viewModel: BaseViewModel,
    lifecycleOwner: LifecycleOwner
) {
    lifecycleOwner.lifecycle.addObserver(viewModel)
}

fun <STATE : Any, VIEW_MODEL> observeCellViewModel(
    viewModel: VIEW_MODEL,
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    withLifecycle: Boolean = true,
    stateDelegate: (STATE) -> Unit
) where VIEW_MODEL : BaseStateViewModel<STATE> {
    if (withLifecycle) {
        observeLifecycle(
            viewModel = viewModel,
            lifecycleOwner = lifecycleOwner
        )
    }
    observeFlow(
        flow = viewModel.stateFlow,
        lifecycleOwner = lifecycleOwner,
        lifecycleState = lifecycleState,
        delegate = stateDelegate
    )
}