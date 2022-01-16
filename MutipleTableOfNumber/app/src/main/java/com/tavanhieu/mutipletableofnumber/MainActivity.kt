package com.tavanhieu.mutipletableofnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var edtNumber: EditText
    lateinit var btnAdd: Button
    lateinit var btnReset: Button
    lateinit var txtResult: TextView
    //StringBuffer: chuỗi có thể thay đổi độ dài.
    //String: Chuỗi được coi như hằng số, không thể thay đổi độ dài.
    var strBuffer: StringBuffer = StringBuffer("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anhXa()

        btnReset.setOnClickListener {
            edtNumber.text = null
            txtResult.text = "Multiple table of number"
            strBuffer.delete(0, strBuffer.length)
        }

        btnAdd.setOnClickListener {
            try {
                //Xóa chuỗi nếu đã có chuỗi chạy trước đó:
                strBuffer.delete(0, strBuffer.length)

                val s = edtNumber.text.trim().toString()
                if (s.isNotEmpty() || s != "") {
                    val a = s.toInt()
                    for (i in 1..10) {
                        strBuffer.append(a.toString() + " x " + i + " = " + a * i + "\n")
                    }
                    txtResult.text = strBuffer
                } else {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ dữ liệu!!!", Toast.LENGTH_SHORT).show()
                }
            } catch (ex: Exception) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun anhXa() {
        edtNumber   = findViewById(R.id.edtNumber)
        btnAdd      = findViewById(R.id.btnAdd)
        btnReset    = findViewById(R.id.btnReset)
        txtResult    = findViewById(R.id.txtResult)
    }
}