package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_disp18.*

class Disp18 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp18)
    }

    fun tap_btnDisp(view : View?){
        btnOK.setVisibility(View.VISIBLE)
        btnNG.setVisibility(View.VISIBLE)
        txtAnswer.setVisibility(View.VISIBLE)
        btnDisp.setVisibility(View.INVISIBLE)
    }

    fun tap_btnOK(view : View?){
        val intent= Intent(this,Disp18::class.java)
        startActivity(intent)
    }

    fun tap_btnNG(view : View?){
        val intent= Intent(this,Disp18::class.java)
        startActivity(intent)
    }
}
