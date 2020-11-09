package com.mudit.getcontrol

import android.content.Context

class AppProvider {

    companion object {

        private lateinit var appContext: Context

        fun init(context: Context) {
            appContext = context.applicationContext
        }

        fun getAppContext(): Context {
            return appContext
        }
    }
}