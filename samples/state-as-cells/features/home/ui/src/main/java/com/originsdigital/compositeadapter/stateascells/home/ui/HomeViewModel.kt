package com.originsdigital.compositeadapter.stateascells.home.ui

import androidx.lifecycle.viewModelScope
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.core.entity.Scene
import com.originsdigital.compositeadapter.core.extensions.isEmpty
import com.originsdigital.compositeadapter.home.core.repository.HomeRepository
import com.originsdigital.compositeadapter.news.core.entity.NewsEntity
import com.originsdigital.compositeadapter.stories.core.entity.StoryEntity
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI
import com.originsdigital.compositeadapter.ui.vm.BaseStateViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val homeUIMapper: HomeUIMapper
) : BaseStateViewModel<HomeViewModel.State>() {

    private val onStoriesRetryClicked: ((ClickItem<CommonErrorUI>) -> Unit) = { onRefresh() }
    private val onNewsRetryClicked: ((ClickItem<CommonErrorUI>) -> Unit) = { onRefresh() }
    private val onStoryClickListener: ((ClickItem<StoryEntity>) -> Unit) = { click ->
    }
    private val onNewsClickListener: ((ClickItem<NewsEntity>) -> Unit) = { click ->
    }

    private val storiesFlow = MutableStateFlow<Scene<List<StoryEntity>>>(Scene.Loading())
    private val newsFlow = MutableStateFlow<Scene<List<NewsEntity>>>(Scene.Loading())

    override val stateFlow: StateFlow<State> = combine(
        storiesFlow,
        newsFlow
    ) { storiesScene, newsScene ->
        homeUIMapper.mapState(
            storiesScene = storiesScene,
            newsScene = newsScene,
            onStoriesRetryClicked = onStoriesRetryClicked,
            onNewsRetryClicked = onNewsRetryClicked,
            onStoryClickListener = onStoryClickListener,
            onNewsClickListener = onNewsClickListener
        )
    }
        .flowOn(bgDispatcher)
        .stateIn(viewModelScope, SharingStarted.Lazily, homeUIMapper.mapState())

    private var storiesJob: Job? = null
    private var newsJob: Job? = null

    init {
        onRefresh()
    }

    override fun onRefresh() {
        loadStories()
        loadNews()
    }

    private fun loadStories(force: Boolean = storiesFlow.value.isEmpty) {
        storiesJob?.cancel()
        storiesJob = loadBlockWithState(
            text = "loadStories",
            force = force,
            sceneFlow = storiesFlow,
            block = { homeRepository.getStories() }
        )
    }

    private fun loadNews(force: Boolean = newsFlow.value.isEmpty) {
        newsJob?.cancel()
        newsJob = loadBlockWithState(
            text = "loadNews",
            force = force,
            sceneFlow = newsFlow,
            block = { homeRepository.getNews() }
        )
    }

    data class State(
        val isRefreshing: Boolean,
        val cells: List<Cell<*>>
    )
}