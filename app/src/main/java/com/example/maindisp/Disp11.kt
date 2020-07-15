package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_disp11.*

class Disp11 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp11)
        strQuestion.setText(GLOBAL.PAGE_NUMBER)
        strAnswer.setText(GLOBAL.ANSWER[0])
    }

    fun tap_btnDone(view : View?){
        GLOBAL.QUESTION[0]=strQuestion.text.toString()
        GLOBAL.ANSWER[0]=strAnswer.text.toString()
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }
    fun tap_btnCancel(view : View?){
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }
}
