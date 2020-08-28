package com.loblaw.redditnews.data.remote.network.retrofit

import com.loblaw.redditnews.data.remote.network.response.Article
import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RedditAPI {
    @GET(".json")
    fun loadArticles(): Single<NewsResponse>
}