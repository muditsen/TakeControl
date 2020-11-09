package com.mudit.getcontrol.utils

import android.app.usage.UsageStatsManager
import android.content.Context
import com.mudit.getcontrol.AppInfo
import com.mudit.getcontrol.AppProvider
import java.util.*
import kotlin.collections.ArrayList

class AppsUtils {

    companion object {
        fun getAppsWithUsageTime(): List<AppInfo> {
            val ctx = AppProvider.getAppContext()
            val packageManager = ctx.packageManager

            val queryManager =
                ctx.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

            val cal: Calendar = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_YEAR, -5)

            val listOfData = queryManager.queryUsageStats(
                UsageStatsManager.INTERVAL_BEST,
                cal.timeInMillis,
                System.currentTimeMillis()
            )

            val appInfoList = ArrayList<AppInfo>()

            for (item in listOfData) {

                try {
                    val appData = packageManager.getApplicationInfo(item.packageName, 0)
                    appInfoList.add(
                        AppInfo(
                            appData.loadLabel(packageManager).toString(),
                            appData.packageName,
                            item.totalTimeVisible,
                            appData.loadIcon(packageManager)
                        )
                    )
                } catch (e: Exception) {

                }


            }

            return appInfoList
        }
    }

}