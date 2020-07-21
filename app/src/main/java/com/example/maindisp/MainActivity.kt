package com.example.maindisp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fab.setOnClickListener { view ->
            //testText.setText("ふろーちんぐおされたわぁ")
            val dialog : ClsTextInputDialog = ClsTextInputDialog(this)
            // ダイアログ用にクラスを作っているのでそこに設定している
            dialog.dialogTitle = "テキスト入力"
            dialog.dialogMessage = "文字を入力してください！"
            dialog.dialogTextData = "教科名"//testText.text.toString()
            dialog.onOkClickListener = DialogInterface.OnClickListener { _, _->
                // OK選択時の処理
                val textData = dialog.dialogTextData
                testText.text = textData
            }
            dialog.isCancelButton = true
            // ダイアログ表示
            dialog.openDialog(supportFragmentManager)
        }


        val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
        var i = 0
        while(GLOBAL.NOTE[i] != null){
            testText.text=GLOBAL.NOTE[i]
            getLayoutInflater().inflate(R.layout.table, vg)
            val tr = vg.getChildAt(i) as TableRow
            ((tr.getChildAt(0))as CheckBox).isChecked()
            ((tr.getChildAt(0))as CheckBox).setTag(i)
            ((tr.getChildAt(1)) as Button).setOnClickListener {
                //この中に処理を書きます
                tap_btnWarpDisp02(it)
            }
            //タグをセットする
            ((tr.getChildAt(1)) as Button).setTag(i)
            //((tr.getChildAt(1)) as Button).setText(((tr.getChildAt(1)) as Button).getTag().toString())
            ((tr.getChildAt(1)) as Button).setText(GLOBAL.NOTE[i])
            i++
        }










        btnWarpDisp02.setOnClickListener{tap_btnWarpDisp02(it)}
        GLOBAL.PAGE_NUMBER=0

        edit.setOnClickListener {
            tap_btnWarpDisp21(it)
        }

    }
    fun tap_btnWarpDisp02(view:View?){
        val intent = Intent(this,Disp02::class.java)
        startActivity(intent)
    }

    fun tap_btnWarpDisp07(view: View?){

        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }

    fun tap_btnWarpDisp21(view:View?) {
        val intent = Intent(this, Disp21::class.java)
        startActivity(intent)
    }
    fun tap_btnWarpDisp08(view :View?){
        val intent= Intent(this,Disp08::class.java)
        startActivity(intent)
    }
    fun tap_btnWarpDisp18(view :View?){
        val intent= Intent(this,Disp18::class.java)
        startActivity(intent)
    }
}
