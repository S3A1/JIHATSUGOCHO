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
        GLOBAL.QUESTION[0]="TEST_QUESTION"
        GLOBAL.ANSWER[0]="TEST_ANSWER"
        GLOBAL.QUESTION[1]="TEST"
        GLOBAL.ANSWER[1]="TEST"
    }

    fun tap_btnWarpDisp07(view:View?){
        GLOBAL.PAGE_NUMBER=0
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }
}
