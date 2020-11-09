package com.mudit.getcontrol

import android.app.Application

class TheApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppProvider.init(this)
    }
}