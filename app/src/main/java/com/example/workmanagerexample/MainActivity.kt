package com.example.workmanagerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = Data.Builder()

        val workRequest = OneTimeWorkRequest
            .Builder(WeatherWorker::class.java)

        val workRequest2 = OneTimeWorkRequest
            .Builder(WeatherWorker::class.java).setInitialDelay(10, TimeUnit.SECONDS)

        data.putString("city", "Иркутск")
        workRequest.setInputData(data.build())

        data.putString("city", "Красноярск")
        workRequest2.setInputData(data.build())

        WorkManager.getInstance(this)
            .beginWith(
                workRequest.build())
            .then(workRequest2.build())
            .enqueue()
    }
}