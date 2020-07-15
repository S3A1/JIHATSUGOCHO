package com.example.maindisp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list.text = GLOBAL.NOTE[0]


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
        list.setOnClickListener{tap_btnWarpDisp02(it)}
        GLOBAL.PAGE_NUMBER=0

        edit.setOnClickListener {
            val text = list.text.toString()
            tap_btnWarpDisp21(it)
        }

    }
    fun tap_btnWarpDisp02(view:View?){
        val intent = Intent(this, Disp02::class.java)
        startActivity(intent)
    }

    fun tap_btnWarpDisp07(view: View?){

        val intent= Intent(this,Disp07::class.java)
        startActivity(intent)
    }

    fun tap_btnWarpDisp21(view:View?) {
        val intent = Intent(this,Disp21::class.java)
    fun tap_btnWarpDisp08(view :View?){
        val intent= Intent(this,Disp08::class.java)
        startActivity(intent)
    }
    fun tap_btnWarpDisp18(view :View?){
        val intent= Intent(this,Disp18::class.java)
        startActivity(intent)
    }
}
