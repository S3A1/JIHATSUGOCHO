package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_disp07.*

class Disp07 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    //この変数に問題と解答を設定する
    val Question:String="問題";
    val Answer:String="解答";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp07)

        setQuestion()
    }

    //次ボタンが押された場合
    fun tap_btnNext(view: View?){
        setQuestion()
    }

    //前ボタンが押された場合
    fun tap_btnBack(view:View?){
        setQuestion()
    }


    fun setQuestion(){
        txtQuestion.setText(GLOBAL.QUESTION[0])
        txtAnswer.setText(GLOBAL.ANSWER[0])
    }

    fun tap_btnChange(view : View?){
        val intent= Intent(this,Disp11::class.java)
        startActivity(intent)
    }

    fun tap_btnHome(view : View?){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}
