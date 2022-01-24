package com.tavanhieu.simplereflex

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var acMain: LinearLayout
    var time = 0L
    var time1 = 0L

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anhXa()

        btnStart.setOnClickListener {
            val ran = Random.nextInt(10)
            Handler(mainLooper).postDelayed({
                //Sau khi delay: Đổi background, Lấy time hiện tại của hệ thống bằng Millis.
                time = System.currentTimeMillis()
                acMain.background = getDrawable(android.R.color.holo_orange_light)
            }, (ran*1000).toLong())
        }

        btnStop.setOnClickListener {
            //Nếu chưa click start thì toast thông báo.
            if(time != 0L) {
                time1 = System.currentTimeMillis()
                if (time1 - time <= 300) {
                    Toast.makeText(this,
                        "Very Good.\nTime hit on stop: ${time1 - time}",
                        Toast.LENGTH_SHORT).show()
                } else if (time1 - time <= 450) {
                    Toast.makeText(this,
                        "Good Point.\nTime hit on stop: ${time1 - time}",
                        Toast.LENGTH_SHORT).show()
                } else if (time1 - time <= 600) {
                    Toast.makeText(this,
                        "Medium Point.\nTime hit on stop: ${time1 - time}",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,
                        "Bad Point.\nTime hit on stop: ${time1 - time}",
                        Toast.LENGTH_SHORT).show()
                }
                //Cài đặt về background cũ, Set time = 0L để tránh việc người chơi click tiếp stop khi chưa start.
                acMain.background = getDrawable(android.R.color.white)
                time = 0L
            } else {
                Toast.makeText(this, "You must click Start first.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun anhXa() {
        btnStart    = findViewById(R.id.btnStart)
        btnStop     = findViewById(R.id.btnStop)
        acMain      = findViewById(R.id.acmain)
    }
}