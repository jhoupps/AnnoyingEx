package com.jhoupps.annoyingex

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

//This is the manager for everything to do with the Work Manager
class ManageTheWorkManagerManager (private val context: Context) {
    private var workManager = WorkManager.getInstance(context)

    //This function starts sending constant notifications every 20 minutes
    fun startAnnoyingTheHeckOuttaPerson() {
        if (isAWTYRunning()) {
            stopWork()
        }

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

    //This function checks to see if the work manager is running
    private fun isAWTYRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(AWTY_WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    //This function stops all work that is currently in progress
    fun stopWork() {
        workManager.cancelAllWorkByTag(AWTY_WORK_REQUEST_TAG)
    }

    //Companion object for the work request tag
    companion object {
        const val AWTY_WORK_REQUEST_TAG = "AWTY_WORK_REQUEST_TAG"
    }

}
