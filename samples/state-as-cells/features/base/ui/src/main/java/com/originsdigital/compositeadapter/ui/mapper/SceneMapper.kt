package com.originsdigital.compositeadapter.ui.mapper

import android.app.Application
import android.util.TypedValue
import androidx.annotation.DimenRes
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.core.entity.Scene
import com.originsdigital.compositeadapter.core.log
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.decoration.SpaceItemDecoration
import com.originsdigital.compositeadapter.ui.cell.CommonErrorCell
import com.originsdigital.compositeadapter.ui.cell.CommonFullErrorCell
import com.originsdigital.compositeadapter.ui.cell.CommonFullLoaderCell
import com.originsdigital.compositeadapter.ui.cell.CommonLoaderCell
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI

class SceneMapper(
    app: Application,
    val loaderUIMapper: LoaderUIMapper,
    val errorUIMapper: ErrorUIMapper
) {

    private val stateItemDecoration: ItemDecoration

    init {
        val defaultSpace = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 40f, app.resources.displayMetrics
        ).toInt()
        stateItemDecoration = SpaceItemDecoration(
            top = defaultSpace,
            bottom = defaultSpace
        )
    }

    fun isAllLoaders(vararg scenes: Scene<*>): Boolean {
        return scenes.isNotEmpty() && scenes.all { scene -> scene is Scene.Loading }
    }

    fun isAllErrors(vararg scenes: Scene<*>): Boolean {
        return scenes.isNotEmpty() && scenes.all { scene -> scene is Scene.Error }
    }

    fun isRefreshing(vararg scenes: Scene<*>): Boolean {
        return scenes.isNotEmpty() && scenes.any { scene -> scene.isRefreshing }
    }

    fun <DATA> getErrorScene(
        mainScene: Scene<DATA>,
        vararg scenes: Scene<*>
    ): Scene.Error<DATA>? {
        return if (mainScene is Scene.Error && isAllErrors(*scenes)) {
            mainScene
        } else {
            null
        }
    }

    fun <DATA, CONTENT> mapScene(
        scene: Scene<DATA>,
        loadingDelegate: (scene: Scene.Loading<DATA>) -> CONTENT,
        errorDelegate: (scene: Scene.Error<DATA>) -> CONTENT,
        dataDelegate: (Scene.Data<DATA>) -> CONTENT
    ): CONTENT {
        log(this, "mapSceneToCells=${Thread.currentThread()}")
        return when (scene) {
            is Scene.Loading -> loadingDelegate(scene)
            is Scene.Error -> errorDelegate(scene)
            is Scene.Data -> dataDelegate(scene)
        }
    }

    fun <DATA> mapSceneToCell(
        scene: Scene<DATA>,
        loadingDelegate: (scene: Scene.Loading<DATA>) -> GenericCell = { getFullLoaderCell() },
        errorDelegate: (scene: Scene.Error<DATA>) -> GenericCell,
        dataDelegate: (Scene.Data<DATA>) -> GenericCell
    ): GenericCell {
        return mapScene(
            scene = scene,
            loadingDelegate = loadingDelegate,
            errorDelegate = errorDelegate,
            dataDelegate = dataDelegate
        )
    }

    fun <DATA> mapSceneToCellOrNull(
        scene: Scene<DATA>,
        loadingDelegate: (scene: Scene.Loading<DATA>) -> GenericCell? = { getFullLoaderCell() },
        errorDelegate: (scene: Scene.Error<DATA>) -> GenericCell?,
        dataDelegate: (Scene.Data<DATA>) -> GenericCell?
    ): GenericCell? {
        return mapScene(
            scene = scene,
            loadingDelegate = loadingDelegate,
            errorDelegate = errorDelegate,
            dataDelegate = dataDelegate
        )
    }

    fun <DATA> mapSceneToCells(
        scene: Scene<DATA>,
        loadingDelegate: (scene: Scene.Loading<DATA>) -> List<GenericCell> = {
            listOf(getFullLoaderCell())
        },
        errorDelegate: (scene: Scene.Error<DATA>) -> List<GenericCell>,
        dataDelegate: (Scene.Data<DATA>) -> List<GenericCell>
    ): List<GenericCell> {
        return mapScene(
            scene = scene,
            loadingDelegate = loadingDelegate,
            errorDelegate = errorDelegate,
            dataDelegate = dataDelegate
        )
    }

    fun <DATA> mapSceneToCells(
        scene: Scene<DATA>,
        onRetryClicked: (GenericClickItem<CommonErrorUI>) -> Unit,
        dataDelegate: (Scene.Data<DATA>) -> List<GenericCell>
    ): List<GenericCell> {
        return mapScene(
            scene = scene,
            loadingDelegate = { listOf(getFullLoaderCell()) },
            errorDelegate = { errorScene ->
                listOf(
                    getFullErrorCell(
                        error = errorUIMapper.mapError(
                            id = "FullErrorCell",
                            throwable = errorScene.throwable
                        ),
                        decoration = null,
                        onRetryClicked = onRetryClicked
                    )
                )
            },
            dataDelegate = dataDelegate
        )
    }

    fun <DATA> mapSmallSceneToCell(
        scene: Scene<DATA>,
        uniqueId: String,
        onRetryClicked: ((GenericClickItem<CommonErrorUI>) -> Unit),
        dataDelegate: (Scene.Data<DATA>) -> GenericCell
    ): GenericCell {
        return mapSceneToCell(
            scene = scene,
            loadingDelegate = { getSmallLoaderCell(uniqueLoaderId = "loader=$uniqueId") },
            errorDelegate = { errorScene ->
                getSmallErrorCell(
                    error = errorUIMapper.mapError(
                        id = "error=$uniqueId",
                        throwable = errorScene.throwable
                    ),
                    decoration = stateItemDecoration,
                    onRetryClicked = onRetryClicked
                )
            },
            dataDelegate = dataDelegate
        )
    }

    fun <DATA> mapSmallSceneToCellOrNull(
        scene: Scene<DATA>,
        uniqueId: String,
        onRetryClicked: ((GenericClickItem<CommonErrorUI>) -> Unit),
        dataDelegate: (Scene.Data<DATA>) -> GenericCell?
    ): GenericCell? {
        return mapSceneToCellOrNull(
            scene = scene,
            loadingDelegate = { getSmallLoaderCell(uniqueLoaderId = uniqueId) },
            errorDelegate = { errorScene ->
                getSmallErrorCell(
                    error = errorUIMapper.mapError(
                        id = "error=$uniqueId",
                        throwable = errorScene.throwable,
                        heightId = null
                    ),
                    decoration = stateItemDecoration,
                    onRetryClicked = onRetryClicked
                )
            },
            dataDelegate = dataDelegate
        )
    }

    fun <DATA> mapSmallSceneToCells(
        scene: Scene<DATA>,
        uniqueId: String,
        onRetryClicked: ((GenericClickItem<CommonErrorUI>) -> Unit),
        dataDelegate: (Scene.Data<DATA>) -> List<GenericCell>
    ): List<GenericCell> {
        return mapSceneToCells(
            scene = scene,
            loadingDelegate = { listOf(getSmallLoaderCell(uniqueLoaderId = uniqueId)) },
            errorDelegate = { errorScene ->
                listOf(
                    getSmallErrorCell(
                        error = errorUIMapper.mapError(
                            id = "error=$uniqueId",
                            throwable = errorScene.throwable,
                            heightId = null
                        ),
                        decoration = stateItemDecoration,
                        onRetryClicked = onRetryClicked
                    )
                )
            },
            dataDelegate = dataDelegate
        )
    }

    fun getFullLoaderCell(): GenericCell {
        return CommonFullLoaderCell()
    }

    fun getSmallLoaderCell(
        uniqueLoaderId: String,
        decoration: ItemDecoration? = stateItemDecoration,
        @DimenRes height: Int? = null
    ): GenericCell {
        return CommonLoaderCell(
            data = loaderUIMapper.mapLoader(
                id = uniqueLoaderId,
                heightId = height
            ),
            decoration = decoration
        )
    }

    fun getErrorCell(
        isFull: Boolean,
        error: CommonErrorUI,
        decoration: ItemDecoration? = stateItemDecoration,
        onRetryClicked: (GenericClickItem<CommonErrorUI>) -> Unit
    ): GenericCell {
        return if (isFull) {
            getFullErrorCell(
                error = error,
                decoration = null,
                onRetryClicked = onRetryClicked
            )
        } else {
            getSmallErrorCell(
                error = error,
                decoration = decoration,
                onRetryClicked = onRetryClicked
            )
        }
    }

    fun getFullErrorCell(
        error: CommonErrorUI,
        decoration: ItemDecoration? = null,
        onRetryClicked: (GenericClickItem<CommonErrorUI>) -> Unit
    ): GenericCell {
        return CommonFullErrorCell(
            data = error,
            decoration = decoration,
            onClickListener = onRetryClicked
        )
    }

    fun getSmallErrorCell(
        error: CommonErrorUI,
        decoration: ItemDecoration? = stateItemDecoration,
        onRetryClicked: (GenericClickItem<CommonErrorUI>) -> Unit
    ): GenericCell {
        return CommonErrorCell(
            data = error,
            decoration = decoration,
            onClickListener = onRetryClicked
        )
    }
}