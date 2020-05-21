package com.jhoupps.annoyingex


import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class ManageTheWorkManagerManager (private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingTheHeckOuttaPerson() {
        if (isAWTYRunning()) {
            stopWork()
        }

        //TODO - CHECK IF THIS CONSTRAINT IS NEEDED
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        PeriodicWorkRequestBuilder<PesterBaeWorker>(15, TimeUnit.MINUTES)

        val workRequest = PeriodicWorkRequestBuilder<PesterBaeWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(AWTY_WORK_REQUEST_TAG)
            .build()


        workManager.enqueue(workRequest)

    }

    private fun isAWTYRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(AWTY_WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(AWTY_WORK_REQUEST_TAG)
    }


    companion object {
        const val AWTY_WORK_REQUEST_TAG = "AWTY_WORK_REQUEST_TAG"
    }

}
