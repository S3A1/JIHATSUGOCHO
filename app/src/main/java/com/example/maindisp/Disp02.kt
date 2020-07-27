package com.example.maindisp

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_disp02.*
import kotlinx.android.synthetic.main.content_disp02.*
import kotlinx.android.synthetic.main.content_main.*

class Disp02 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp02)
        setSupportActionBar(toolbar)

        val page = intent.getIntExtra("PAGE_NUM",-1)

        //btnDisp07.text = GLOBAL.NOTE[page]

        btnDisp07.text = page.toString()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    fun tap_btnList(view : View?){
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }


}
