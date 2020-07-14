package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //insert test
    }

    fun tap_btnWarpDisp07(view: View?){
        GLOBAL.QUESTION[0]="Q1"
        GLOBAL.ANSWER[0]="A1"
        GLOBAL.QUESTION[1]="Q2"
        GLOBAL.ANSWER[1]="A2"
        GLOBAL.QUESTION[2]="Q3"
        GLOBAL.ANSWER[2]="A3"
        GLOBAL.NOTE_NUMBER=0;
        GLOBAL.PAGE_NUMBER=0;
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }
}
