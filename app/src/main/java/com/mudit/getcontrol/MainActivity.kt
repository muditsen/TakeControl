package com.mudit.getcontrol

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mudit.getcontrol.utils.AppsUtils
import com.mudit.getcontrol.utils.Wallpaper
import java.util.stream.LongStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_getControl).setOnClickListener {
            findViewById<TextView>(R.id.tv_getControl).post {
                Wallpaper.setWallpaper(Wallpaper.convertToBitmap(findViewById<TextView>(R.id.tv_getControl).rootView))
            }
        }


        var max = AppInfo("", "", Long.MIN_VALUE, null)
        val list = AppsUtils.getAppsWithUsageTime()
        if (list.isEmpty()) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }
        for (i in list) {
            if (max.totalTimeUsed < i.totalTimeUsed) {
                max = i
            }
        }


        max.iconResource?.let {
            findViewById<ImageView>(R.id.iv_appIcon).setImageDrawable(it)
        }


        // startService(Intent(this, LockScreenService::class.java))
    }
}