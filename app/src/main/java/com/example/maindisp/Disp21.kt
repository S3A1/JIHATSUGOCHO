package com.example.maindisp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_disp21.*
import kotlinx.android.synthetic.main.content_main.*

class Disp21 : AppCompatActivity() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp21)
        setSupportActionBar(toolbar)

        list.text = GLOBAL.NOTE[0]

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        list.setOnClickListener {
            val dialog : ClsTextInputDialog = ClsTextInputDialog(this)
            // ダイアログ用にクラスを作っているのでそこに設定している
            dialog.dialogTitle = "タイトルの編集"
            dialog.dialogMessage = "新しいタイトルを入力してください"
            dialog.dialogTextData = "教科名"//testText.text.toString()
            dialog.onOkClickListener = DialogInterface.OnClickListener { _, _->
                // OK選択時の処理
                val textData = dialog.dialogTextData
                GLOBAL.NOTE[0] = textData
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                //finish()
            }
            dialog.isCancelButton = true
            // ダイアログ表示
            dialog.openDialog(supportFragmentManager)
        }
    }
}
