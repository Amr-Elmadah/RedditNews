package com.loblaw.redditnews.ui.articleslist.domain.repository

import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import com.loblaw.redditnews.ui.articleslist.data.remote.ArticlesRemoteDataSource
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val remoteDataSource: ArticlesRemoteDataSource
) : ArticlesRepository {
    override fun getArticles(): Single<NewsResponse> =
        remoteDataSource.getArticles()

    override suspend fun getArticlesWithCoroutines(): Flow<NewsResponse> =
        remoteDataSource.getArticlesWithCoroutines()
}