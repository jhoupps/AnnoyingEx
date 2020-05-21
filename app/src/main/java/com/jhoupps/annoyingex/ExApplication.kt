package com.jhoupps.annoyingex

import android.app.Application

//This is the application for my app
class ExApplication: Application() {
    lateinit var manageTheWorkManagerManager: ManageTheWorkManagerManager
        private set //only can be set internally - nobody else can set it

    lateinit var exNotificationManager: ExNotificationManager
        private set

    override fun onCreate() {
        super.onCreate()

        manageTheWorkManagerManager = ManageTheWorkManagerManager(this)
        exNotificationManager = ExNotificationManager(this)
    }


}