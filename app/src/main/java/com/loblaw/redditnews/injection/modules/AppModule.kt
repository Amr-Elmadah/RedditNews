package com.loblaw.redditnews.injection.modules


import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}
