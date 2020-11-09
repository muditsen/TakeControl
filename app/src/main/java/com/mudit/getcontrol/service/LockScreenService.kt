package com.mudit.getcontrol.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.mudit.getcontrol.R
import com.mudit.getcontrol.receiver.LockScreenReceiver


class LockScreenService : Service() {

    companion object {
        private val NOTIFICATION_CHANNEL_ID = "takeControlApp"
        private val NOTIFICATION_CHANNEL_NAME = "Take Control"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_USER_PRESENT)
        val mReceiver: BroadcastReceiver = LockScreenReceiver()
        registerReceiver(mReceiver, filter)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification: Notification =
            NotificationCompat.Builder(this, getNotificationChannelId(notificationManager))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(getString(R.string.app_take_control))
                .setPriority(PRIORITY_MIN)
                .build()

        startForeground(123, notification)
    }

    private fun getNotificationChannelId(notificationManager: NotificationManager?): String {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance)
            notificationChannel.enableLights(false)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(false)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager?.createNotificationChannel(notificationChannel)
            NOTIFICATION_CHANNEL_ID
        } else
            ""
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}