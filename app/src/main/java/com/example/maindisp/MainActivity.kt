package com.example.maindisp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {
    val GLOBAL=MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list.text = GLOBAL.NOTE[0]


        fab.setOnClickListener { view ->
            val dialog : ClsTextInputDialog = ClsTextInputDialog(this)
            // ダイアログ用にクラスを作っているのでそこに設定している
            dialog.dialogTitle = "テキスト入力"
            dialog.dialogMessage = "文字を入力してください！"
            dialog.dialogTextData = "教科名"//testText.text.toString()
            //ここはヒント表示に切り替える
            dialog.onOkClickListener = DialogInterface.OnClickListener { _, _->
                // OK選択時の処理
                GLOBAL.AddNoteName(dialog.dialogTextData)
            }
            dialog.isCancelButton = true
            // ダイアログ表示
            dialog.openDialog(supportFragmentManager)
        }
        list.setOnClickListener{tap_btnWarpDisp02(it)}
        GLOBAL.PAGE_NUMBER=0

        edit.setOnClickListener {
            tap_btnWarpDisp21(it)
        }

    }

    //終了時処理
    override fun onDestroy(){
        super.onDestroy()
        DataSave()
    }

    fun DataSave(){
        try {
            for(i in 0..19){
                if(GLOBAL.NOTE[i]!=null){
                    try{
                        val file=File("$filesDir/" +GLOBAL.NOTE[i] +".csv")
                        file.writeText(CreateCSV(i))
                    }catch(e:Exception){

                    }
                }
                else{
                    break
                }
            }
        }catch(e:Exception){

        }

    }
    fun CreateCSV(i:Int):String{
        var strBuffer:String=""
        //保存データを作成
        for(n in 0..119){
            if(GLOBAL.QUESTION[i*120+n]!=null){
                strBuffer+=GLOBAL.QUESTION[i*120+n]+","+GLOBAL.ANSWER[i*120+n]+"\n"
            }
        }
        return strBuffer
    }

    fun tap_btnWarpDisp02(view:View?){
        val intent = Intent(this, Disp02::class.java)
        startActivity(intent)
    }

    fun tap_btnWarpDisp07(view: View?){
        GLOBAL.NOTE_NUMBER=1
        GLOBAL.PAGE_NUMBER=0
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
        GLOBAL.PAGE_NUMBER=0
        val intent= Intent(this,Disp18::class.java)
        startActivity(intent)
    }
    fun tap_btnFileTest(view : View?){
        val fileName1 = "$filesDir" + "/果物の漢字.csv"
        val fileName2 = "$filesDir" + "/元素記号.csv"
        val fileName3 = "$filesDir"+"/県庁所在地.csv"

        val text1     = "林檎,りんご\n葡萄,ぶどう\n桜桃,さくらんぼ\n枇杷,びわ\n檸檬,れもん"
        val text2     = "1:H,水素\n2:He,ヘリウム\n3:Li,リチウム\n4:Be,ベリリウム\n5:B,ホウ素\n6:C,炭素\n7:N,窒素\n8:O,酸素\n9:F,フッ素\n10:Ne,ネオン"
        val text3="宮城,仙台\n群馬,前橋\n栃木,宇都宮\n茨城,水戸\n福岡,福岡\n沖縄,那覇"

        try{
            val writeFile1 = File(fileName1)
            writeFile1.writeText(text1)
            val  writeFile2= File(fileName2)
            writeFile2.writeText(text2)
            val writeFile3 = File(fileName3)
            writeFile3.writeText(text3)
        } catch (e: FileNotFoundException){
            println(e)
        }
    }
}
