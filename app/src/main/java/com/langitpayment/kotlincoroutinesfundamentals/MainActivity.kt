package com.langitpayment.kotlincoroutinesfundamentals

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("HAI", Thread.currentThread().name)

        /**
         * call into another thread
         * see for details https://developer.android.com/training/multiple-threads/define-runnable
         * */
        Thread(Runnable {
            Log.d("HAI", Thread.currentThread().name)
            val imageUrl = URL("https://avatars2.githubusercontent.com/u/51395880?s=280&v=4")
            val connection = imageUrl.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)

            /**
             * post to the main thread use
             * */
            runOnUiThread{
                Log.d("HAI", Thread.currentThread().name)
                image.setImageBitmap(bitmap)
            }
        }).start()
    }
}