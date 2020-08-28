package com.loblaw.redditnews.injection.context

import com.loblaw.redditnews.ui.articleslist.injection.ArticlesModule
import com.loblaw.redditnews.ui.articleslist.presetation.view.activity.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(ArticlesModule::class)])
    abstract fun bindArticlesActivity(): ArticlesActivity
}

