package com.loblaw.redditnews.base.domain.interactor

import com.loblaw.redditnews.data.remote.network.response.NewsResponse
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

abstract class SingleUseCase<in Params, Type> where Type : Any {

    abstract fun build(params: Params): Single<Type>

    abstract suspend fun buildWithCoroutines(params: Params): Flow<NewsResponse>
}