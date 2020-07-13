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
        GLOBAL.QUESTION[0]="TEST_Q"
        GLOBAL.ANSWER[0]="TEST_A"
    }

    fun tap_btnWarpDisp07(view:View?){
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }
}
