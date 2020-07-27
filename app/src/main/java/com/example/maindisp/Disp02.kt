package com.example.maindisp

import android.content.DialogInterface
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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_disp02.*
import kotlinx.android.synthetic.main.content_main.*

class Disp02 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp02)
        setSupportActionBar(toolbar)

        val NOTEtxt = GLOBAL.NOTE[GLOBAL.NOTE_NUMBER]



        val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
        var i = 0
        while(GLOBAL.NOTE[i] != null){
            testText.text=GLOBAL.NOTE[i]
            getLayoutInflater().inflate(R.layout.table, vg)
            val tr = vg.getChildAt(i) as TableRow
            ((tr.getChildAt(0))as CheckBox).isChecked()
            ((tr.getChildAt(0))as CheckBox).setTag(i)
            ((tr.getChildAt(1)) as Button).setTag(i)
            ((tr.getChildAt(1)) as Button).setText(GLOBAL.NOTE[i])
            ((tr.getChildAt(1)) as Button).setOnClickListener {
                GLOBAL.NOTE_NUMBER=Integer.parseInt(it.getTag().toString())
                GLOBAL.PAGE_NUMBER=0
                tap_btnWarpDisp07(it)
            }
            i++
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.disp07_menu, menu)
        return true
    }

    fun tap_btnList(view : View?){
        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }
    fun tap_btnWarpDisp07(view: View?){

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.Edit -> {
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
