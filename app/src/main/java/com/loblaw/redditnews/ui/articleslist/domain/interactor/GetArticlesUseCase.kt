package com.loblaw.redditnews.ui.articleslist.domain.interactor

import com.loblaw.redditnews.base.domain.interactor.SingleUseCase
import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import com.loblaw.redditnews.ui.articleslist.domain.repository.ArticlesRepository
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val repository: ArticlesRepository) :
    SingleUseCase<String, NewsResponse>() {
    override fun build(params: String): Single<NewsResponse> =
        repository.getArticles()

    override suspend fun buildWithCoroutines(params: String): Flow<NewsResponse> =
        repository.getArticlesWithCoroutines()
}