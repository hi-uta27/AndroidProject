package com.tavanhieu.simplemusicplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btnPlayNext:       ImageButton
    private lateinit var btnPlayStart:      ImageButton
    private lateinit var btnPlayStop:       ImageButton
    private lateinit var tvNameOfSong:      TextView
    private lateinit var tvNameOfSinger:    TextView
    private lateinit var media:             MediaPlayer
    private var isPlay = false
    private var i = 0
    private val arrName = ArrayList<String>()
    private val arrNameSinger = ArrayList<String>()
    private val arrSong = ArrayList<Int>()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anhXa()
        addArray()
        //Khởi tạo bài hát khi chạy lên:
        media = MediaPlayer.create(this, arrSong[i])

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun playNext(view: View) {
        //Dừng bài hiện tại và chạy bài tiếp theo:
        //------------------------------------------------------------------------------------------
        media.stop()  //Dừng và xóa bỏ bài hát hiện tại.
        i++ //Lấy vị trí bài sau.
        if(i == arrSong.size) i = 0 //Nếu vượt quá mảng thì về bài hát đầu tiên.
        media = MediaPlayer.create(this, arrSong[i]) //Tạo lại bài hát.

        tvNameOfSong.text = arrName[i] //Gán tên bài hát.
        tvNameOfSinger.text = arrNameSinger[i] //Gán tên ca sĩ.
        btnPlayStart.setImageDrawable(getDrawable(R.drawable.pause)) //Gán icon.

        media.start() //Bắt đầu luôn bài hát mới.
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    fun playStart(view: View) {
        val btn: ImageButton = view as ImageButton

        //isPlay = true: thì cho chạy bài hát và hiển thị: tên bài hát, tên ca sĩ, icon pause.
        //isPlay = false: Tạm dừng bài hát, icon play.
        if(!isPlay) {
            media.start()

            tvNameOfSong.text = arrName[i]
            tvNameOfSinger.text = arrNameSinger[i]
            btn.setImageDrawable(getDrawable(R.drawable.pause))
        } else {
            media.pause()

            btn.setImageDrawable(getDrawable(R.drawable.play))
        }
        isPlay = !isPlay
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    fun playStop(view: View) {
        //Dừng bài hát.
        media.stop()

        isPlay = false //Gán về trang thái play(false) để chạy khi play lại.
        btnPlayStart.setImageDrawable(getDrawable(R.drawable.play)) //Gán về icon play.
        media = MediaPlayer.create(this, arrSong[i]) //Tạo lại bài hát do Stop() đã xóa bỏ bài tạo trước đó.
    }

    private fun anhXa() {
        btnPlayNext     = findViewById(R.id.btnNext)
        btnPlayStart    = findViewById(R.id.btnStart_Pause)
        btnPlayStop     = findViewById(R.id.btnStop)
        tvNameOfSong    = findViewById(R.id.tvNameOfSong)
        tvNameOfSinger    = findViewById(R.id.tvNameOfSinger)
    }
    private fun addArray() {
        arrName.add("Em Đây Chẳng Phải Thúy Kiều")
        arrName.add("Em Khác Gì Hoa")
        arrName.add("Em Thích")
        arrName.add("Mang Tiền Về Cho Mẹ")
        arrName.add("Gieo Quẻ")
        arrName.add("Nắm Bàn Tay Say Cả Đời")
        arrName.add("Rồi Nâng Cái Ly")
        arrName.add("Yêu Là Cưới")
        arrName.add("Dạ Vũ")

        arrNameSinger.add("Hoàng Thùy Linh")
        arrNameSinger.add("Lil Zpoet")
        arrNameSinger.add("")
        arrNameSinger.add("Đen")
        arrNameSinger.add("Hoàng Thùy Linh x Đen")
        arrNameSinger.add("Đạt Trần x Nâu")
        arrNameSinger.add("Orinn Remix")
        arrNameSinger.add("Phát Hồ x Hương Ly")
        arrNameSinger.add("Tăng Duy Tân x VRT")

        arrSong.add(R.raw.em_day_chang_phai_thuy_kieu)
        arrSong.add(R.raw.em_khac_gi_hoa)
        arrSong.add(R.raw.emthich)
        arrSong.add(R.raw.mang_tien_ve_cho_me)
        arrSong.add(R.raw.gieo_que)
        arrSong.add(R.raw.nam_ban_tay_say_ca_doi)
        arrSong.add(R.raw.roi_nang_cai_ly)
        arrSong.add(R.raw.yeu_la_cuoi)
        arrSong.add(R.raw.da_vu)
    }
}