package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.setOnClickListener{tapDisp02(it)}

    }
    fun tapDisp02(view: View?){
        val intent = Intent(this, Disp02::class.java)
        startActivity(intent)
    }
}
