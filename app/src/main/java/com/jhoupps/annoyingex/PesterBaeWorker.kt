package com.jhoupps.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class PesterBaeWorker(private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {


    override fun doWork(): Result {

        Log.i("jhoupps", "i sure hope bae-bee answers me this time")

        var exNotificationManager = ExNotificationManager(context)
        exNotificationManager.postItNote()

        return Result.success()
    }
}