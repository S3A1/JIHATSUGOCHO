package com.example.maindisp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_disp02.*
import kotlinx.android.synthetic.main.activity_disp02.fab
import kotlinx.android.synthetic.main.content_disp02.*

class Disp02 : AppCompatActivity() {

    var i = 0
    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp02)
        setSupportActionBar(toolbar)
        CreatePage()
    }




    fun CreatePage(){
        val vg = findViewById<View>(R.id.tableLayout) as ViewGroup

        var num = 0
        while(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+i] != null){
            getLayoutInflater().inflate(R.layout.singletable, vg)
            //getLayoutInflater().inflate(R.layout.singletable, vg)
            if(num==0){
                val tr = vg.getChildAt(i) as TableRow
                /*((tr.getChildAt(1))as Button).setText("テストモード")
                ((tr.getChildAt(1)) as Button).setOnClickListener {
                    GLOBAL.PAGE_NUMBER=0
                    val intent= Intent(this,Disp18::class.java)
                    startActivity(intent)
                }*/
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
        i--

        fab.setOnClickListener { view ->
            val intent= Intent(this,Disp08::class.java)
            startActivity(intent)
        }

        btncancel.setOnClickListener {
            fab.setVisibility(View.VISIBLE)
            btncancel.setVisibility(View.INVISIBLE)
            btndelApply.setVisibility(View.INVISIBLE)
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
                fab.setVisibility(View.INVISIBLE)
                btncancel.setVisibility(View.VISIBLE)
                btndelApply.setVisibility(View.VISIBLE)
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
