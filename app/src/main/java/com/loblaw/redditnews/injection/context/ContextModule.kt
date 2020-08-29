package com.loblaw.redditnews.injection.context

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

open class ContextModule(private val appContext: Context) {

	fun provideContext() = appContext
}