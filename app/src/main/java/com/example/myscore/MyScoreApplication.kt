package com.example.myscore

import android.app.Application
import com.example.myscore.injection.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyScoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyScoreApplication)
            modules(Modules.all)
        }
    }
}