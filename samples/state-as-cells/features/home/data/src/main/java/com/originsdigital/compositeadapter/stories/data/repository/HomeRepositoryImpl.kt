package com.originsdigital.compositeadapter.stories.data.repository

import android.graphics.Color
import com.originsdigital.compositeadapter.home.core.repository.HomeRepository
import com.originsdigital.compositeadapter.news.core.entity.NewsEntity
import com.originsdigital.compositeadapter.stories.core.entity.StoryEntity
import kotlinx.coroutines.delay
import kotlin.random.Random

class HomeRepositoryImpl : HomeRepository {

    override suspend fun getStories(): List<StoryEntity> {
        loadData()
        val colors = listOf(
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.DKGRAY,
            Color.GRAY,
            Color.GREEN,
            Color.LTGRAY,
            Color.MAGENTA,
            Color.RED,
            Color.YELLOW
        )
        return (0..20).map { index ->
            StoryEntity(
                id = index.toString(),
                colorInt = colors[index % colors.size],
                name = "Story $index"
            )
        }
            .filter { Random.nextBoolean() }
    }

    override suspend fun getNews(): List<NewsEntity> {
        loadData()
        return (0..50).map { index ->
            NewsEntity(id = index.toString(), text = "News $index")
        }
            .filter { Random.nextBoolean() }
    }

    private suspend fun loadData() {
        val delay = 500 + Random.nextLong(500)
        delay(delay)
        if (delay > 800) {
            throw NullPointerException()
        }
    }
}