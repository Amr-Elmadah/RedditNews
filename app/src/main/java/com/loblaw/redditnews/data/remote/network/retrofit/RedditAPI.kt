package com.loblaw.redditnews.data.remote.network.retrofit

import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface RedditAPI {
    @GET(".json")
    fun loadArticles(): Single<NewsResponse>

    @GET(".json")
    suspend fun loadArticlesWithCoroutines(): NewsResponse
}