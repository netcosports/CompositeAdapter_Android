package com.originsdigital.compositeadapter.stateascells.home.ui

import android.app.Application
import android.content.Context
import android.util.TypedValue
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.core.entity.Scene
import com.originsdigital.compositeadapter.decoration.SpaceItemDecoration
import com.originsdigital.compositeadapter.news.core.entity.NewsEntity
import com.originsdigital.compositeadapter.news.ui.cell.NewsCell
import com.originsdigital.compositeadapter.stories.core.entity.StoryEntity
import com.originsdigital.compositeadapter.stories.ui.cell.StoriesCell
import com.originsdigital.compositeadapter.stories.ui.cell.StoryCell
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI
import com.originsdigital.compositeadapter.ui.mapper.SceneMapper

class HomeUIMapper(
    app: Application,
    private val sceneMapper: SceneMapper
) {

    private val firstStoryItemDecoration: SpaceItemDecoration<Cell<*>>
    private val middleStoryBigItemDecoration: SpaceItemDecoration<Cell<*>>
    private val lastStoryItemDecoration: SpaceItemDecoration<Cell<*>>
    private val firstNewsItemDecoration: SpaceItemDecoration<Cell<*>>
    private val middleNewsBigItemDecoration: SpaceItemDecoration<Cell<*>>
    private val lastNewsItemDecoration: SpaceItemDecoration<Cell<*>>

    init {
        firstStoryItemDecoration = SpaceItemDecoration(
            top = app.dpToPx(8f).toInt(),
            bottom = app.dpToPx(8f).toInt(),
            start = app.dpToPx(8f).toInt()
        )
        middleStoryBigItemDecoration = firstStoryItemDecoration
        lastStoryItemDecoration = SpaceItemDecoration(
            top = firstStoryItemDecoration.top,
            bottom = firstStoryItemDecoration.bottom,
            start = firstStoryItemDecoration.start,
            end = firstStoryItemDecoration.start
        )
        firstNewsItemDecoration = SpaceItemDecoration(
            top = app.dpToPx(8f).toInt(),
            start = app.dpToPx(8f).toInt(),
            end = app.dpToPx(8f).toInt()
        )
        middleNewsBigItemDecoration = firstNewsItemDecoration
        lastNewsItemDecoration = SpaceItemDecoration(
            top = firstStoryItemDecoration.top,
            bottom = firstStoryItemDecoration.top,
            start = firstStoryItemDecoration.start,
            end = firstStoryItemDecoration.end
        )
    }

    private fun Context.dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }

    fun mapState(): HomeViewModel.State {
        return HomeViewModel.State(
            isRefreshing = false,
            cells = emptyList()
        )
    }

    fun mapState(
        storiesScene: Scene<List<StoryEntity>>,
        newsScene: Scene<List<NewsEntity>>,
        onStoriesRetryClicked: ((ClickItem<CommonErrorUI>) -> Unit),
        onNewsRetryClicked: ((ClickItem<CommonErrorUI>) -> Unit),
        onStoryClickListener: ((ClickItem<StoryEntity>) -> Unit),
        onNewsClickListener: ((ClickItem<NewsEntity>) -> Unit)
    ): HomeViewModel.State {
        val storiesCell = sceneMapper.mapSmallSceneToCell(
            scene = storiesScene,
            uniqueId = "stories",
            onRetryClicked = onStoriesRetryClicked,
            dataDelegate = { dataScene -> mapStories(dataScene, onStoryClickListener) }
        )
        val newsCells = sceneMapper.mapSmallSceneToCells(
            scene = newsScene,
            uniqueId = "news",
            onRetryClicked = onNewsRetryClicked,
            dataDelegate = { dataScene -> mapNews(dataScene, onNewsClickListener) }
        )
        return HomeViewModel.State(
            isRefreshing = sceneMapper.isRefreshing(storiesScene, newsScene),
            cells = listOf(storiesCell) + newsCells
        )
    }

    private fun mapStories(
        storiesScene: Scene.Data<List<StoryEntity>>,
        onStoryClickListener: ((ClickItem<StoryEntity>) -> Unit)
    ): Cell<*> {
        return StoriesCell(
            data = storiesScene.data.mapIndexed { index, story ->
                val decoration = when (index) {
                    0 -> firstStoryItemDecoration
                    storiesScene.data.size - 1 -> lastStoryItemDecoration
                    else -> middleStoryBigItemDecoration
                }
                StoryCell(
                    data = story,
                    decoration = decoration,
                    onClickListener = onStoryClickListener
                )
            }
        )
    }

    private fun mapNews(
        newsScene: Scene.Data<List<NewsEntity>>,
        onNewsClickListener: ((ClickItem<NewsEntity>) -> Unit)
    ): List<Cell<*>> {
        return newsScene.data.mapIndexed { index, story ->
            val decoration = when (index) {
                0 -> firstNewsItemDecoration
                newsScene.data.size - 1 -> lastNewsItemDecoration
                else -> middleNewsBigItemDecoration
            }
            NewsCell(
                data = story,
                decoration = decoration,
                onClickListener = onNewsClickListener
            )
        }
    }
}