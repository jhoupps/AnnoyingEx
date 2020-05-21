package com.jhoupps.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val exApp = (application as ExApplication)
        val manageTheWorkManagerManager = exApp.manageTheWorkManagerManager

        btnAnnoyStart.setOnClickListener {
            manageTheWorkManagerManager.startAnnoyingTheHeckOuttaPerson()
        }

        btn_OneNotification.setOnClickListener {
            exApp.exNotificationManager.postItNote() //todo apparently we should use poking the bear manager here
        }

        btnCancel.setOnClickListener{
            manageTheWorkManagerManager.stopWork()
            Toast.makeText(this, "Successfully Blocked \"That F@#%ing B@st@rd\"",Toast.LENGTH_SHORT ).show()
        }

        //test data form until I get the api set up
        //TODO - CALL THIS FROM THE INTERNET


    }

}
