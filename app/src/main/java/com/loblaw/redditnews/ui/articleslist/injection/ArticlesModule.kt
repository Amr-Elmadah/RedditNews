package com.loblaw.redditnews.ui.articleslist.injection

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.loblaw.redditnews.data.remote.network.retrofit.RedditAPI
import com.loblaw.redditnews.ui.articleslist.data.remote.ArticlesRemoteDataSource
import com.loblaw.redditnews.ui.articleslist.domain.interactor.GetArticlesUseCase
import com.loblaw.redditnews.ui.articleslist.domain.repository.ArticlesRepository
import com.loblaw.redditnews.ui.articleslist.domain.repository.ArticlesRepositoryImp
import com.loblaw.redditnews.ui.articleslist.presetation.view.adapter.ArticlesAdapter
import com.loblaw.redditnews.ui.articleslist.presetation.viewmodel.ArticlesViewModel
import dagger.Module
import dagger.Provides

@Module
class ArticlesModule {

    @Provides
    fun provideArticlesRemoteDataSource(redditAPI: RedditAPI) =
        ArticlesRemoteDataSource(redditAPI = redditAPI)

    @Provides
    fun provideArticlesRepository(
        remoteDataSource: ArticlesRemoteDataSource
    ): ArticlesRepository =
        ArticlesRepositoryImp(remoteDataSource)

    @Provides
    fun provideGetArticlesUseCase(repository: ArticlesRepository) =
        GetArticlesUseCase(repository)

    @Provides
    fun provideArticlesViewModel(
        getArticlesUseCase: GetArticlesUseCase
    ) =
        ArticlesViewModel(getArticlesUseCase)

    @Provides
    fun provideLinearLayoutManager(context: Context) =
        LinearLayoutManager(context)

    @Provides
    fun provideArticlesAdapter() =
        ArticlesAdapter()
}