package com.originsdigital.compositeadapter.ui.vm

import androidx.lifecycle.viewModelScope
import com.originsdigital.compositeadapter.core.entity.Scene
import com.originsdigital.compositeadapter.core.extensions.toData
import com.originsdigital.compositeadapter.core.extensions.toError
import com.originsdigital.compositeadapter.core.extensions.toLoading
import com.originsdigital.compositeadapter.core.log
import com.originsdigital.compositeadapter.core.utils.safeLoad
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class BaseStateViewModel<STATE : Any> : BaseDataViewModel() {

    abstract val stateFlow: Flow<STATE>

    protected fun <DATA> loadBlockWithState(
        text: String,
        force: Boolean,
        currentSceneDelegate: () -> Scene<DATA>,
        newSceneDelegate: (Scene<DATA>) -> Unit,
        block: (suspend CoroutineScope.() -> DATA)
    ): Job {
        return viewModelScope.launch {
            withContext(bgDispatcher) {
                safeLoad(
                    scope = this,
                    text = text,
                    force = force,
                    currentSceneDelegate = currentSceneDelegate,
                    newSceneDelegate = newSceneDelegate,
                    block = block
                )
            }
        }
    }

    protected fun <DATA> loadBlockWithState(
        text: String,
        force: Boolean,
        sceneFlow: MutableStateFlow<Scene<DATA>>,
        block: (suspend CoroutineScope.() -> DATA)
    ): Job {
        return loadBlockWithState(
            text = text,
            force = force,
            currentSceneDelegate = { sceneFlow.value },
            newSceneDelegate = { newScene -> sceneFlow.value = newScene },
            block = block
        )
    }

    protected fun <DATA> loadFlowWithState(
        text: String,
        force: Boolean,
        currentSceneDelegate: () -> Scene<DATA>,
        newSceneDelegate: (Scene<DATA>) -> Unit,
        flow: Flow<DATA>
    ): Job {
        val tag = this@BaseStateViewModel
        return viewModelScope.launch {
            flow
                .catch { error ->
                    log(tag, "$text error = $error")
                    newSceneDelegate(currentSceneDelegate().toError(error = error, force = force))
                }
                .onStart {
                    log(tag, "$text onStart")
                    newSceneDelegate(currentSceneDelegate().toLoading(force = force))
                }
                .collect { data ->
                    log(tag, "$text collect = $data")
                    newSceneDelegate(currentSceneDelegate().toData(data = data))
                }
        }
    }

    private suspend fun <DATA> safeLoad(
        scope: CoroutineScope,
        text: String,
        force: Boolean,
        currentSceneDelegate: () -> Scene<DATA>,
        newSceneDelegate: (Scene<DATA>) -> Unit,
        block: (suspend CoroutineScope.() -> DATA)
    ) {
        safeLoad<Scene<DATA>>(
            tag = this@BaseStateViewModel,
            text = text,
            block = {
                newSceneDelegate(currentSceneDelegate().toLoading(force = force))
                currentSceneDelegate()
                    .toData(data = block(scope))
                    .also { newScene -> newSceneDelegate(newScene) }
            },
            error = { error ->
                currentSceneDelegate()
                    .toError(error = error, force = force)
                    .also { newScene -> newSceneDelegate(newScene) }
            }
        )
    }
}