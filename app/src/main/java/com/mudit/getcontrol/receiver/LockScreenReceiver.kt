package com.mudit.getcontrol.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class LockScreenReceiver : BroadcastReceiver() {

    companion object {
        val TAG = "ScreenLockReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Mudit Sen ", Toast.LENGTH_LONG).show()
        val action = intent!!.action
        when {
            Intent.ACTION_SCREEN_ON == action -> {
                Log.e(TAG, "screen is on...")
            }
            Intent.ACTION_SCREEN_OFF == action -> {
                Log.e(TAG, "screen is off...")
            }
            Intent.ACTION_USER_PRESENT == action -> {
                Log.e(TAG, "screen is unlock...")
            }
        }
    }

}