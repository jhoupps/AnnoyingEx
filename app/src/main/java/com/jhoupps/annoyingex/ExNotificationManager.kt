package com.jhoupps.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
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


    //todo make changes here when i go online
    fun getMessage(): String {
        //todo this should be from the internet
        val messagebank = listOf<String>(
            "hey u up?",
            "wyd",
            "baby im sorry",
            "can we talk?",
            "i miss youuuuu",
            "i love you",
            "im sorry, plz come back",
            "plz unblock me",
            "call me back asap",
            "just thinkin about you tonight",
            "why wont you talk to me",
            "please can you just call me",
            "can we talk in person",
            "do you ever think about us",
            "lol",
            "hows your day going",
            "so this is it huh?",
            "you used to call me on my cellphone late night when you need my love",
            "so who was that in your IG story",
            "im bored",
            "hey Facetime? call? text?... Zoom?",
            "Good morning :)",
            "i just think its funny how... ",
            "hahahahahahahahahahahahahaha thats a good facebook post",
            "din tai fung?",
            "28th times a charm?",
            "baby...SHARK Du-du-du-duuuu, baby shark!"
        )

        //check for if it doesnt work
        val message =  messagebank.random()
        return message


    }



    companion object {
        const val FUN_CHANNEL_ID = "FUNCHANNELID"
    }



}