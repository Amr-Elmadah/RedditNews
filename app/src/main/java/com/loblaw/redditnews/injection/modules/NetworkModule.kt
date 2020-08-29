package com.loblaw.redditnews.injection.modules

import com.loblaw.redditnews.BuildConfig
import com.loblaw.redditnews.data.remote.network.retrofit.RedditAPI
import com.loblaw.redditnews.data.remote.network.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
open class NetworkModule {

    object DAGGER_CONSTANTS {
        const val BASE_URL = "baseUrlString"
    }

    private object ApiEndpointsConstants {
        const val ProductionURL = "https://www.reddit.com/r/kotlin/"
        const val StagingURL = "https://www.reddit.com/r/kotlin/"
    }

    @Provides
    @Singleton
    @Named(value = DAGGER_CONSTANTS.BASE_URL)
    fun providesBaseUrl() =
        if (BuildConfig.QA) ApiEndpointsConstants.StagingURL else ApiEndpointsConstants.ProductionURL

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @Named(DAGGER_CONSTANTS.BASE_URL) baseURL: String, httpClient: OkHttpClient,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        builder: Retrofit.Builder
    )
            : Retrofit = RetrofitClient(
        baseURL = baseURL,
        httpClient = httpClient.newBuilder(),
        httpLoggingInterceptor = httpLoggingInterceptor,
        builder = builder
    ).getInstance()

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit)
            : RedditAPI = retrofit.create(RedditAPI::class.java)

}