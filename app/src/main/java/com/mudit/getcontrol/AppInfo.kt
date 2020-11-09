package com.mudit.getcontrol

import android.graphics.drawable.Drawable

data class AppInfo(
    val appName: String,
    val packageName:String,
    val totalTimeUsed:Long,
    val iconResource:Drawable?

)