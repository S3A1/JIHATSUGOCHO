package com.example.maindisp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        setLastLabel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.disp18_menu, menu)
        return true
    }

    fun tap_btnDisp(view : View?){
        btnOK.setVisibility(View.VISIBLE)
        btnNG.setVisibility(View.VISIBLE)
        txtAnswer.setVisibility(View.VISIBLE)
        btnDisp.setVisibility(View.INVISIBLE)
    }

    fun tap_btnOK(view : View?){
        GLOBAL.LAST[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]=1
        if(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER+1]!=null){
            GLOBAL.PAGE_NUMBER+=1
            val intent= Intent(this,Disp18::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val intent= Intent(this,Disp22::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun tap_btnNG(view : View?){
        GLOBAL.LAST[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]=0
        if(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER+1]!=null){
            GLOBAL.PAGE_NUMBER+=1
            val intent= Intent(this,Disp18::class.java)
            startActivity(intent)
            finish()
        }
        else {
            val intent = Intent(this, Disp22::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun tap_btnTestEnd(view : View?){
        val intent= Intent(this,Disp22::class.java)
        startActivity(intent)
        finish()
    }

    fun setQuestion(){
        txtQuestion.setText(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER])
        txtAnswer.setText(GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER])
    }

    fun setLastLabel() {
        when(GLOBAL.LAST[GLOBAL.NOTE_NUMBER*120+GLOBAL.PAGE_NUMBER]){
            -1 ->{
                txtLast.setVisibility(View.INVISIBLE)
            }
            0 ->{
                txtLast.setVisibility(View.VISIBLE)
                txtLast.setText("前回 : ×")
            }
            1 ->{
                txtLast.setVisibility(View.VISIBLE)
                txtLast.setText("前回 : ○")
            }
        }//when end
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.End -> {
                val intent = Intent(this, Disp22::class.java)
                startActivity(intent)
                finish()
                return true
            }
            R.id.Home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
