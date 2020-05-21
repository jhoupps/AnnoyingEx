package com.jhoupps.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var exApp: ExApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        exApp = (application as ExApplication)
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

        //Call the JSON from the api
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                Log.i("jhoupps", "Response is: ${response.substring(0, 500)}")
                parseData(response)
            },
            Response.ErrorListener { Log.i("jhoupps",  "That didn't work!") })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }


    fun parseData(theData: String){
        val gson = Gson()

        val allMessages: AllMessages = gson.fromJson(theData, AllMessages::class.java)


        allMessages.messages.let {
            Log.i("jhoupps", "Parsed a content of: $it")
        }

        exApp.exNotificationManager.messagebank = allMessages.messages


    }

}

//Models
data class AllMessages( val messages: List<String>)