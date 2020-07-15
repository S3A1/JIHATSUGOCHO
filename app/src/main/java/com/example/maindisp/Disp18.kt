package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_disp18.*
import kotlinx.android.synthetic.main.activity_disp18.btnNG
import kotlinx.android.synthetic.main.activity_disp18.btnOK
import kotlinx.android.synthetic.main.activity_disp18.txtQuestion
import kotlinx.android.synthetic.main.activity_disp18.txtAnswer

class Disp18 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp18)
        setQuestion()
    }

    fun tap_btnDisp(view : View?){
        btnOK.setVisibility(View.VISIBLE)
        btnNG.setVisibility(View.VISIBLE)
        txtAnswer.setVisibility(View.VISIBLE)
        btnDisp.setVisibility(View.INVISIBLE)
    }

    fun tap_btnOK(view : View?){
        if(GLOBAL.QUESTION[GLOBAL.PAGE_NUMBER+1]!=null){
            GLOBAL.PAGE_NUMBER+=1
            val intent= Intent(this,Disp18::class.java)
            startActivity(intent)
        }
        else{
            val intent= Intent(this,Disp22::class.java)
            startActivity(intent)
        }
    }

    fun tap_btnNG(view : View?){
        if(GLOBAL.QUESTION[GLOBAL.PAGE_NUMBER+1]!=null){
            GLOBAL.PAGE_NUMBER+=1
            val intent= Intent(this,Disp18::class.java)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, Disp22::class.java)
            startActivity(intent)
        }
    }

    fun tap_btnTestEnd(view : View?){
        val intent= Intent(this,Disp22::class.java)
        startActivity(intent)
    }

    fun setQuestion(){
        txtQuestion.setText(GLOBAL.QUESTION[GLOBAL.PAGE_NUMBER])
        txtAnswer.setText(GLOBAL.ANSWER[GLOBAL.PAGE_NUMBER])
    }

}
