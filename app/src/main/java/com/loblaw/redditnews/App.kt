package com.loblaw.redditnews

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initLeakCanary()
    }

    private fun initStetho() {
        if (BuildConfig.QA) {
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build()
            )
        }
    }

    private fun initLeakCanary() {
        LeakCanary.install(this)
    }
}