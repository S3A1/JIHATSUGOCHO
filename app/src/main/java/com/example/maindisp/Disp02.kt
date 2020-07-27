package com.example.maindisp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TableRow
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_disp02.*
import kotlinx.android.synthetic.main.activity_disp02.fab
import kotlinx.android.synthetic.main.content_disp02.*

class Disp02 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp02)
        setSupportActionBar(toolbar)

        val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
        var i = 0
        var num = 0
        while(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+i] != null){
            getLayoutInflater().inflate(R.layout.table, vg)
            if(num==0){
                val tr = vg.getChildAt(i) as TableRow
                ((tr.getChildAt(1))as Button).setText("テストモード")
                ((tr.getChildAt(1)) as Button).setOnClickListener {
                    GLOBAL.PAGE_NUMBER=0
                    val intent= Intent(this,Disp18::class.java)
                    startActivity(intent)
                }
                num++
            }else{
                val tr = vg.getChildAt(num) as TableRow
                ((tr.getChildAt(0))as CheckBox).isChecked()
                ((tr.getChildAt(0))as CheckBox).setTag(i)
                ((tr.getChildAt(1)) as Button).setTag(i)
                ((tr.getChildAt(1)) as Button).setText(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+i])
                ((tr.getChildAt(1)) as Button).setOnClickListener {
                    GLOBAL.PAGE_NUMBER=Integer.parseInt(it.getTag().toString())
                    val intent= Intent(this,Disp07::class.java)
                    startActivity(intent)
                }
                num++
                i++
            }

        }

        

        fab.setOnClickListener { view ->
            val intent= Intent(this,Disp08::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.disp02_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.End -> {
                return true
            }
            R.id.Delete -> {
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



//https://android.roof-balcony.com/activity/back-key-event/
//戻るボタンの処理を追加する、削除実行時に空白が選択できる問題あり
