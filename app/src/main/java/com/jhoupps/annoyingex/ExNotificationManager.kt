package com.jhoupps.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class ExNotificationManager (
    private val context: Context
) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createFunChannel()
    }


    fun postItNote() {
        val notification = NotificationCompat.Builder(context, FUN_CHANNEL_ID)
            //.setSmallIcon(R.drawable.ic_smoke_free_black_24dp)
            .setContentTitle("Post Malone")
            .setContentText("You a SUNNN FLOOOOWWWWWAAAAAAAAAA ${Random.nextInt()}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
          //  .setContentIntent(pendingDealsIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createFunChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Fun Notifications"
            val descriptionText = "All Msgs from a great autotune voiced dude"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(FUN_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val FUN_CHANNEL_ID = "FUNCHANNELID"
    }

}