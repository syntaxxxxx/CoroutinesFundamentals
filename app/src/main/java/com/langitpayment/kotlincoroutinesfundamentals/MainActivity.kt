package com.langitpayment.kotlincoroutinesfundamentals

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Thread", Thread.currentThread().name)

        GlobalScope.launch(context = Dispatchers.IO) {
            Log.d("Thread", Thread.currentThread().name)

            val imageUrl = URL("https://avatars2.githubusercontent.com/u/51395880?s=280&v=4")
            val connection = imageUrl.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)

            launch(Dispatchers.Main) {
                Log.d("Thread", Thread.currentThread().name)
                image.setImageBitmap(bitmap)
            }
        }
    }
}