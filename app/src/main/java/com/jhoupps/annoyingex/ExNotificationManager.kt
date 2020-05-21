package com.jhoupps.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class ExNotificationManager (
    private val context: Context
) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)
    var messagebank = listOf<String>() //empty until filled by main activity

    init {
        createFunChannel()
    }


    fun postItNote() {
        // Create an Intent for the activity you want to start
        val mainIntent = Intent(context, MainActivity::class.java)
        // Create the TaskStackBuilder
        val mainPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(mainIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }




        val notification = NotificationCompat.Builder(context, FUN_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            .setContentTitle("That F@#%ing B@st@rd")
            .setContentText(getMessage())
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(mainPendingIntent)
            .setAutoCancel(true) //closes notification upon being tapped
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


    private fun getMessage(): String {

        var message = "THIS WILL BE OVERWRITTEN"

        message = if (messagebank.isNotEmpty()){
            messagebank.random()
        } else {
            Log.i("jhoupps", "I decided that there was an error!")
            Log.i("jhoupps", messagebank.toString())
            "unable to retrieve message"
        }
        return message
    }



    companion object {
        const val FUN_CHANNEL_ID = "FUNCHANNELID"
    }



}