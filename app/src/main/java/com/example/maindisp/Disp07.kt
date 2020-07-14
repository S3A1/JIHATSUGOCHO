package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
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
        txtListName.setText(GLOBAL.NOTE[GLOBAL.NOTE_NUMBER])
        txtAnswer.setVisibility(View.INVISIBLE)
        setQuestion();
    }

    //次ボタン押された場合
    fun tap_btnNext(view: View?){
        LoopNumber(1)
        setQuestion()
        txtAnswer.setVisibility(View.INVISIBLE)
    }

    //前ボタンが押された場合
    fun tap_btnBack(view:View?){
        LoopNumber(-1)
        setQuestion()
        txtAnswer.setVisibility(View.INVISIBLE)
    }


    fun setQuestion(){
        txtQuestion.setText(GLOBAL.QUESTION[GLOBAL.PAGE_NUMBER])
        txtAnswer.setText(GLOBAL.ANSWER[GLOBAL.PAGE_NUMBER])
    }

    fun tap_btnChange(view : View?){
        val intent= Intent(this,Disp11::class.java)
        startActivity(intent)
    }

    fun tap_btnHome(view : View?){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun tap_btnDelete(view : View?){
        //ここに削除のダイアログを表示するようにする　※担当吉田に代わります
        DeleteQuestion()
        setQuestion();
    }

    fun DeleteQuestion(){
        //削除対象が最終番であった場合は削除しポインタを一つ戻す
        if(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER+1]==null){
            GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]=null
            GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]=null
            GLOBAL.PAGE_NUMBER-=1;
        }
        else {
            //削除対象から後の項番をひとつづつずらす
            for (i in GLOBAL.PAGE_NUMBER..118) {
                GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER * 120 + i] = GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER * 120 + (i + 1)]
                GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER * 120 + i] = GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER * 120 + (i + 1)]
            }
        }
        //末尾にnullを追加
        GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+119]=null
        GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER*120+119]=null

    }


    //このへんから
    fun LoopNumber(i:Int){//引数　+1で次の番号へ -1で前の番号へ
        if(i==1){
            if(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER+1]==null){
                GLOBAL.PAGE_NUMBER=0
            }
            else{
                GLOBAL.PAGE_NUMBER+=1;
            }
        }
        if(i==-1) {
            if (GLOBAL.PAGE_NUMBER == 0) {
                for (n in 0..119) {
                    if (GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER * 120 + n] == null) {
                        GLOBAL.PAGE_NUMBER = n - 1
                        break;
                    }
                }
            }
            else{
                GLOBAL.PAGE_NUMBER-=1;
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent):Boolean
    {
        when(event!!.action){
            MotionEvent.ACTION_DOWN -> {
                if(txtAnswer.getVisibility()==View.VISIBLE){
                    txtAnswer.setVisibility(View.INVISIBLE)
                }
                else if(txtAnswer.getVisibility()==View.INVISIBLE){
                    txtAnswer.setVisibility(View.VISIBLE)
                }

            }
        }
        return false//onTouchEventの終了
    }

}
