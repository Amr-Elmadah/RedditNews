package com.loblaw.redditnews.ui.articleslist.domain.repository

import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getArticles(): Single<NewsResponse>

    suspend fun getArticlesWithCoroutines(): Flow<NewsResponse>
}