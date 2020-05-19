package com.jhoupps.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class AskAQuestionWorker(private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {


    override fun doWork(): Result {

        Log.i("jhoupps", "this is the ask a question worker doing a thing")

        return Result.success()
    }
}