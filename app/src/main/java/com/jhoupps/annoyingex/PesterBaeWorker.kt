package com.jhoupps.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

//This class is the worker
//It is called every 20 minutes by the ManageTheWorkManagerManger
//It then calls the ExNotificationClass in order to send a notification every 20 minutes
class PesterBaeWorker(private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {


    override fun doWork(): Result {

        Log.i("jhoupps", "i sure hope bae-bee answers me this time")


        var exNotificationManager = (context.getApplicationContext() as ExApplication).exNotificationManager
        exNotificationManager.postItNote()

        return Result.success()
    }
}