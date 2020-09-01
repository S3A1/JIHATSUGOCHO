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
        strQuestion.setText(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER])
        strAnswer.setText(GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER])
    }

    fun tap_btnDone(view : View?){
        GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]=strQuestion.text.toString()
        GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]=strAnswer.text.toString()
        GLOBAL.LAST[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]=-1
        if(GLOBAL.FLG == true){
            val intent = Intent(this, Disp02::class.java)
            startActivity(intent)
            GLOBAL.FLG ==false
            finish()
        }else{
            val intent = Intent(this, Disp07::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun tap_btnCancel(view : View?){
        if(GLOBAL.FLG == true){
            val intent = Intent(this, Disp02::class.java)
            startActivity(intent)
            GLOBAL.FLG ==false
            finish()
        }else{
            val intent = Intent(this, Disp07::class.java)
            startActivity(intent)
            finish()
        }
    }
}
