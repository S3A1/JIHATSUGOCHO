package com.example.maindisp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class StartDisp : AppCompatActivity() {

    var mHandler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.maindisp.R.layout.activity_disp_start)
        val thread = Thread(Runnable {
            try {
                Thread.sleep(750)
                mHandler.post {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })
        thread.start()
    }

}
