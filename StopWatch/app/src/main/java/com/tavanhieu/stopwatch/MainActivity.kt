package com.tavanhieu.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var btnStart: Button
    private lateinit var btnReset: Button
    private lateinit var tvTime: TextView
    private var btnIsPlay = false
    private var second = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anhXa()
        mRunnable()

        btnReset.setOnClickListener {
            second = 0
            tvTime.text = "00:00:00"
            btnIsPlay = false
            btnStart.text = "Start"
            btnStart.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.play, 0, 0, 0)
        }
    }

    private fun anhXa() {
        btnStart = findViewById(R.id.btnStart)
        btnReset = findViewById(R.id.btnReset)
        tvTime   = findViewById(R.id.tvTime)
    }

    fun changeIcon(view: View) {
        val btn: Button = view as Button
        //btnIsPlay = false: Hiển thị nút start và dừng không cho chạy time.
        //btnIsPlay = true: Hiển thị nút stop và cho chạy time.
        if(!btnIsPlay) {
            //Khi click vào sẽ chuyển sang nút "Stop"
            btn.text = "Stop"
            //Set icon:
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.pause, 0, 0, 0)
        } else {
            //Khi click vào sẽ chuyển sang nút "Start"
            btn.text = "Start"
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.play, 0, 0, 0)
        }
        btnIsPlay = !btnIsPlay
    }

    private fun mRunnable() {
        val handler = Handler(mainLooper) //Use mainLooper or Looper.getMainLooper for Handler() is deprecated.
        handler.post(object : Runnable {
            override fun run() {
                val hours: Int = second / 3600
                val minutes: Int = (second % 3600) / 60
                val sec: Int = second % 3600 % 60

                //btnIsPlay == true: Khi chuyển sang nút stop thì bắt đầu chạy.
                if(btnIsPlay) second++
                tvTime.text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, sec)
                //Sau 1s thì sẽ thực hiện hàm Run của Runnable.
                handler.postDelayed(this, 1000)
            }
        })
    }
}