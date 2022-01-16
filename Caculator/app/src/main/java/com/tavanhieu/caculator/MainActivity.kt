package com.tavanhieu.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    lateinit var edtNumberA: EditText
    lateinit var edtNumberB: EditText
    lateinit var txtResult: TextView
    lateinit var grvPhepTinh: GridView
    var arr: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anhXa()
        addArray()

        val madapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
        grvPhepTinh.adapter = madapter

        grvPhepTinh.setOnItemClickListener { parent, view, position, id ->
            val s1 = edtNumberA.text.trim().toString()
            val s2 = edtNumberB.text.trim().toString()
            if((s1.isNotEmpty() && s2.isNotEmpty()) || (s1 != "" && s2 != "")) {
                val a = s1.toDouble()
                val b = s2.toDouble()
                when (position) {
                    0 -> {
                        txtResult.text = (a + b).toString()
                    }
                    1 -> {
                        txtResult.text = (a - b).toString()
                    }
                    2 -> {
                        txtResult.text = (a * b).toString()
                    }
                    3 -> {
                        txtResult.text = (a / b).toString()
                    }
                    4 -> {
                        txtResult.text = (a.pow(b)).toString()
                    }
                    5 -> {
                        txtResult.text = (a % b).toString()
                    }
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ dữ liệu.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun anhXa() {
        edtNumberA = findViewById(R.id.edtNumberA)
        edtNumberB = findViewById(R.id.edtNumberB)
        txtResult   = findViewById(R.id.txtResult)
        grvPhepTinh = findViewById(R.id.grvPhepTinh)
    }
    private fun addArray() {
        arr.add("+")
        arr.add("-")
        arr.add("*")
        arr.add("/")
        arr.add("^")
        arr.add("%")
    }
}