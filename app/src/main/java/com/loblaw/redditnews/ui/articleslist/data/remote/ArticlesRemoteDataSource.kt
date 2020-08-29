package com.loblaw.redditnews.ui.articleslist.data.remote

import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import com.loblaw.redditnews.data.remote.network.retrofit.RedditAPI
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(private val redditAPI: RedditAPI) {

    fun getArticles(): Single<NewsResponse> =
        redditAPI.loadArticles()

    suspend fun getArticlesWithCoroutines(): Flow<NewsResponse> =
        flow { emit(redditAPI.loadArticlesWithCoroutines()) }
}