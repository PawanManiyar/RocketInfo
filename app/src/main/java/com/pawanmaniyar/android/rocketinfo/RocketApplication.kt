package com.pawanmaniyar.android.rocketinfo

import android.app.Application
import com.pawanmaniyar.android.rocketinfo.di.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RocketApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin() {
            androidContext(this@RocketApplication)
            modules(diModule)
        }
    }
}
