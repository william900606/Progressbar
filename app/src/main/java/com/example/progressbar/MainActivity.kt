package com.example.progressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //progressbar動畫
        val buttonStart = findViewById<Button>(R.id.button)
        val buttonEnd = findViewById<Button>(R.id.button2)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        buttonStart.setOnClickListener{
            progressBar.visibility = View.VISIBLE
        }
        buttonEnd.setOnClickListener{
            progressBar.visibility = View.INVISIBLE
        }
        //progressbar進度條
        var i = 0
        val buttonbar = findViewById<Button>(R.id.button3)
        val progressBar2 = findViewById<ProgressBar>(R.id.progressBar2)
        val textView = findViewById<TextView>(R.id.textView)
        val handler = Handler()
        buttonbar.setOnClickListener{
            progressBar2.visibility = View.VISIBLE
            i = progressBar.progress
            //使用Thread()在背景執行，最後一定要加start()
            Thread(Runnable{
                while(i < 100){
                    i +=1
                    //handler非同步處理
                    handler.post(Runnable{
                        progressBar2.progress = i
                        textView!!.text = i.toString() + "/" +progressBar2.max
                    })
                    Thread.sleep(100)
                    println("目前進度: ${i}")
                }
                progressBar2.visibility = View.INVISIBLE
            }).start()
        }

    }
}