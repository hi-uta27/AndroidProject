package com.tavanhieu.videoplayer

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var videoPlayer: VideoView
        var currentVideo = 0
        var arr: ArrayList<Int> = ArrayList()
        fun msetVideo(id: Int) {
            //UriPath vid trong local:
            val uriPath = "android.resource://com.tavanhieu.videoplayer/$id"
            val uri = Uri.parse(uriPath)
            videoPlayer.setVideoURI(uri)
            videoPlayer.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            //Ánh xạ:
            videoPlayer = findViewById(R.id.videoPlayer)
            /*Sử dụng MediaController có sẵn: Khi ấn vào video sẽ hiện lên Controller và mất sau 3s
                                             Tua lên, Tua xuống, Play, Pause, Kéo time lời bài hát sẽ chạy theo */
            videoPlayer.setMediaController(MediaController(this))
            //Khi video kết thúc sẽ hiện 1 dialog: Replay | Next
            videoPlayer.setOnCompletionListener {
                val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                alert.setTitle("Finished video.")
                alert.setIcon(R.drawable.ic_launcher_background)
                //Sự kiện khi click vào button trên dialog:
                val m = MyListener()
                alert.setPositiveButton("Replay", m) // Yes
                alert.setNegativeButton("Next", m)   // No
                alert.setMessage("Do you want to replay or next video?")
                alert.show()
            }
            addArray()
            msetVideo(arr[0])
        }catch (ex:Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addArray() {
        arr.add(R.raw.nucuoiemgiolanang)
        arr.add(R.raw.emthich)
    }
}

class MyListener : DialogInterface.OnClickListener {
    override fun onClick(dialog: DialogInterface?, which: Int) {
        if(which == -1) {
            //PositiveButton:
            MainActivity.videoPlayer.seekTo(0) //Time bài hát về 0s.
            MainActivity.videoPlayer.start()
        } else {
            //NegativeButton:
            MainActivity.currentVideo++
            //Nếu vị trí video tiếp theo bằng size của Array thì đặt thành vid đầu tiên:
            if(MainActivity.currentVideo == MainActivity.arr.size) MainActivity.currentVideo = 0
            //start bài hát tiếp theo:
            MainActivity.msetVideo(MainActivity.arr[MainActivity.currentVideo])
        }
    }
}
