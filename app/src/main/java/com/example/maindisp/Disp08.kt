package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_disp08.*

class Disp08 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp08)
    }

    fun tap_btnAdd(view : View?){
        //最後の項番に文字を登録
        for(i in 0..119){
            if(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+i]==null){
                GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+i]=strQuestion.text.toString()
                GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER*120+i]=strAnswer.text.toString()
                break
            }
        }
        val intent= Intent(this,Disp02::class.java)
        startActivity(intent)
        finish()
    }

    fun tap_btnCancel(view:View?){
        val intent= Intent(this,Disp02::class.java)
        startActivity(intent)
        finish()
    }
}
