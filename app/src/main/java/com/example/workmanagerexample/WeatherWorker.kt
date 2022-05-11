package com.example.workmanagerexample

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import androidx.work.Worker
import java.io.InputStream
import java.net.URL
import java.util.*

class WeatherWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        Log.i("mytag", "doWork")

        return try {

            val city =  inputData.getString("city")

            val weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=d6843ab8ee963f5d372296dfff62aed7&units=metric"
            val stream = URL(weatherURL).getContent() as InputStream
            val data = Scanner(stream).nextLine()

            Log.w("mytag", "Город: $city")
            Log.i("mytag", data)
            Log.i("mytag", "work success")

            Result.success()
        } catch (error: Throwable) {
            Log.e("mytag", "work failure - $error")
            Result.failure()
        }
    }
}