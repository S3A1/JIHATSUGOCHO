package com.example.maindisp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_disp02.*
import kotlinx.android.synthetic.main.activity_disp02.fab
import kotlinx.android.synthetic.main.content_disp02.*

class Disp02 : AppCompatActivity() {

    var i = 0
    val GLOBAL=MyApp.getInstance()
    var btnflg = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp02)
        CreatePage()
        setSupportActionBar(appbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.disp02_menu, menu)
        return true
    }

    fun CreatePage(){
        appbar.setTitle(GLOBAL.NOTE[GLOBAL.NOTE_NUMBER])
        val vg = findViewById<View>(R.id.tableLayout) as ViewGroup

        var num = 0
        while(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+i] != null) {
            getLayoutInflater().inflate(R.layout.singletable, vg)
            if (num == 0) {
                val tr = vg.getChildAt(i) as TableRow
                ((tr.getChildAt(1)) as Button).setText("テストモード")
                ((tr.getChildAt(1)) as Button).setOnClickListener {
                    GLOBAL.PAGE_NUMBER = 0
                    val intent = Intent(this, Disp18::class.java)
                    startActivity(intent)
                }
                num++
            } else {
                val tr = vg.getChildAt(num) as TableRow
                ((tr.getChildAt(0)) as CheckBox).isChecked()
                ((tr.getChildAt(0)) as CheckBox).setTag(i)
                ((tr.getChildAt(1)) as Button).setTag(i)
                ((tr.getChildAt(1)) as Button).setText(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER * 120 + i])
                ((tr.getChildAt(1)) as Button).setOnClickListener {
                    if(btnflg == 0){
                        GLOBAL.PAGE_NUMBER = Integer.parseInt(it.getTag().toString())
                        val intent = Intent(this, Disp07::class.java)
                        startActivity(intent)
                    }else if(btnflg ==1){
                        //編集
                        val intent = Intent(this, Disp11::class.java)
                        GLOBAL.FLG = true
                        startActivity(intent)
                    }
                }
                num++
                i++
            }
        }

        fab.setOnClickListener { view ->
            val intent= Intent(this,Disp08::class.java)
            startActivity(intent)
        }

        btncancel.setOnClickListener {
            val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
            for(s in 1 until i+1){
                val tr = vg.getChildAt(s) as TableRow
                ((tr.getChildAt(0))as CheckBox).setVisibility(View.GONE)
                ((tr.getChildAt(1))as Button).setEnabled(true)
            }
            val tr = vg.getChildAt(0) as TableRow
            ((tr.getChildAt(1))as Button).setVisibility(View.VISIBLE)

            fab.setVisibility(View.VISIBLE)
            btncancel.setVisibility(View.INVISIBLE)
            btndelApply.setVisibility(View.INVISIBLE)
        }
        btndelApply.setOnClickListener {

            val checklist = mutableListOf<Int>()
            val list:List<Int> = checklist
            var lastnum: Int = 0
            var cnt:Int = 0
            btnflg = 0

            val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
            getLayoutInflater().inflate(R.layout.singletable, vg)
            //sからi(問題の数)ループしてチェックされているボタンのタグを取得
            for(s in 1 until i+1){
                val tr = vg.getChildAt(s) as TableRow
                if(((tr.getChildAt(0))as CheckBox).isChecked()==true){
                    var num :Int =((tr.getChildAt(0))as CheckBox).getTag().toString().toInt()
                    checklist.add(num)
                    lastnum = num
                    cnt ++
                }
            }
            if(checklist.isNotEmpty()){
                //textView2.setText(checklist[0].toString())
                var setnum:Int =0
                for(s in 0 until i){
                    var flg:Int = 0
                    for(ss in 0 until cnt) {
                        if(checklist[ss] == s){
                            flg = 1
                        }
                    }
                    if(flg == 0){
                        GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER * 120 + setnum] = GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER * 120 + s]
                        GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER *120 + setnum] =GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER * 120 + s]
                        setnum++
                    }
                }
                for(s in setnum until i) {
                    GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER * 120 + s] = null
                    GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER *120 + s] = null
                }
            }else{
                //削除対象が選択されていませんのダイアログを表示

            }
            for(s in 1 until i+1){
                val tr = vg.getChildAt(s) as TableRow
                ((tr.getChildAt(0))as CheckBox).setVisibility(View.GONE)
                ((tr.getChildAt(1))as Button).setEnabled(true)
            }
            val tr = vg.getChildAt(0) as TableRow
            ((tr.getChildAt(1))as Button).setVisibility(View.VISIBLE)

            fab.setVisibility(View.VISIBLE)
            btncancel.setVisibility(View.INVISIBLE)
            btndelApply.setVisibility(View.INVISIBLE)
            val intent = Intent(this, Disp02::class.java)
            startActivity(intent)
            finish()

        }
        btneditcanc.setOnClickListener {
            fab.setVisibility(View.VISIBLE)
            btneditcanc.setVisibility(View.INVISIBLE)
            btnflg = 0
            val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
            val tr = vg.getChildAt(0) as TableRow
            ((tr.getChildAt(1))as Button).setVisibility(View.VISIBLE)
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.End -> {
                btnflg = 1
                val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
                val tr = vg.getChildAt(0) as TableRow
                ((tr.getChildAt(1))as Button).setVisibility(View.INVISIBLE)
                fab.setVisibility(View.INVISIBLE)
                btneditcanc.setVisibility(View.VISIBLE)
                btncancel.setVisibility(View.INVISIBLE)
                btndelApply.setVisibility(View.INVISIBLE)
                appbar.setTitle("編集したい項目を選択してください")
                return true
            }
            R.id.Delete -> {
                val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
                for(s in 1 until i+1){
                    val tr = vg.getChildAt(s) as TableRow
                    ((tr.getChildAt(0))as CheckBox).setVisibility(View.VISIBLE)
                    ((tr.getChildAt(1))as Button).setEnabled(false)
                }
                val tr = vg.getChildAt(0) as TableRow
                ((tr.getChildAt(1))as Button).setVisibility(View.INVISIBLE)
                fab.setVisibility(View.INVISIBLE)
                btncancel.setVisibility(View.VISIBLE)
                btndelApply.setVisibility(View.VISIBLE)
                btneditcanc.setVisibility(View.INVISIBLE)
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
