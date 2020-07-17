package com.example.maindisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_disp__t_e_s_t.*

class Disp_TEST : AppCompatActivity() {

    val GLOBAL = MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp__t_e_s_t)

        str1.setText(GLOBAL.NOTE[0])
        str2.setText(GLOBAL.NOTE[1])
        str3.setText(GLOBAL.NOTE[2])
    }
}