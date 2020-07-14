package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //insert test

        list.setOnClickListener{tap_btnWarpDisp02(it)}
    }
    fun tap_btnWarpDisp02(view:View?){
        val intent = Intent(this, Disp02::class.java)
        startActivity(intent)
    }

    fun tap_btnWarpDisp07(view: View?){
        GLOBAL.QUESTION[0]="Q1"
        GLOBAL.ANSWER[0]="A1"
        GLOBAL.QUESTION[1]="Q2"
        GLOBAL.ANSWER[1]="A2"
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }
}
