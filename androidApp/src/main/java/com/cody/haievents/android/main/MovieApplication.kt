package com.cody.haievents.android.main

import android.app.Application
import com.cody.haievents.android.common.di.appModule
import com.cody.haievents.di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin



class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(appModule + getSharedModules())
        }
    }
}


