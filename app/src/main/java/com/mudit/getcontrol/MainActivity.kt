package com.mudit.getcontrol

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.mudit.getcontrol.utils.AppsUtils
import com.mudit.getcontrol.utils.Wallpaper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_setWallpaper).setOnClickListener {
            findViewById<TextView>(R.id.tv_getControl).post {
                findViewById<TextView>(R.id.tv_setWallpaper).visibility = View.GONE
                Wallpaper.setWallpaper(Wallpaper.convertToBitmap(findViewById<TextView>(R.id.tv_getControl).rootView))
                findViewById<TextView>(R.id.tv_setWallpaper).visibility = View.VISIBLE
            }
        }

        ViewModelProviders.of(this).get()


        Handler(Looper.myLooper()!!).post {

        }

        HandlerThread

        findViewById<TextView>(R.id.tv_getControl).post {
            Thread {
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

                runOnUiThread {
                    max.iconResource?.let {
                        findViewById<ImageView>(R.id.iv_appIcon).setImageDrawable(it)
                        findViewById<TextView>(R.id.tv_setWallpaper).visibility = View.VISIBLE
                    }
                }


            }.start()
        }



        // startService(Intent(this, LockScreenService::class.java))
    }
}