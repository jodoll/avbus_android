package com.johannesdoll.avbus.ui.remotecontrol

import android.app.Application
import com.johannesdoll.avbus.BuildConfig
import timber.log.Timber

class AvBusApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) setupLogging()
    }

    private fun setupLogging() {
        Timber.plant(Timber.DebugTree())
    }
}