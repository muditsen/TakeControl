package com.mudit.getcontrol.utils

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import com.mudit.getcontrol.AppProvider
import java.io.IOException


class Wallpaper {
    companion object {
        fun setWallpaper(bitmap: Bitmap) {
            val myWallpaperManager = WallpaperManager.getInstance(AppProvider.getAppContext())
            try {
                myWallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun setLockScreen(bitmap: Bitmap) {
            val myWallpaperManager = WallpaperManager.getInstance(AppProvider.getAppContext())
            try {
                myWallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun convertToBitmap(view: View): Bitmap {
            val b = Bitmap.createBitmap(
                view.width,
                view.height,
                Bitmap.Config.ARGB_8888
            )

            val c = Canvas(b)
            view.layout(view.left, view.top, view.right, view.bottom)
            view.draw(c)
            return b
        }
    }
}