package com.originsdigital.compositeadapter.home.core.repository

import com.originsdigital.compositeadapter.news.core.entity.NewsEntity
import com.originsdigital.compositeadapter.stories.core.entity.StoryEntity

interface HomeRepository {

    suspend fun getStories(): List<StoryEntity>

    suspend fun getNews(): List<NewsEntity>
}