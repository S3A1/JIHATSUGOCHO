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
                GLOBAL.QUESTION[i]=strQuestion.text.toString()
                GLOBAL.ANSWER[i]=strAnswer.text.toString()
                break;
            }
        }
        val intent= Intent(this,Disp07::class.java)//本来はDisp02に推移
        startActivity(intent)
    }

    fun tap_btnCancel(view:View?){
        finish()
    }
}
