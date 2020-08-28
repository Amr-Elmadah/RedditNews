package com.loblaw.redditnews.injection

import android.app.Application
import com.loblaw.redditnews.App
import com.loblaw.redditnews.injection.context.ActivityBuilder
import com.loblaw.redditnews.injection.modules.AppModule
import com.loblaw.redditnews.injection.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        NetworkModule::class]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}