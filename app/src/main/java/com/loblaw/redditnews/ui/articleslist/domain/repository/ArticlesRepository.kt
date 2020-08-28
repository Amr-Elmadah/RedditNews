package com.loblaw.redditnews.ui.articleslist.domain.repository

import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import io.reactivex.Single

interface ArticlesRepository {
    fun getArticles(): Single<NewsResponse>
}