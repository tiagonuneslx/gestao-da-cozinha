package com.example.gestaodacozinha

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class GestaoCozinhaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }
}