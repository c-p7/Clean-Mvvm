package com.tcs.sample.cleanmvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MvvmCleanApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}