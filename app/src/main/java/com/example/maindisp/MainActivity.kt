package com.example.maindisp


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.w3c.dom.Text
import java.io.File
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {
    val GLOBAL=MyApp.getInstance()
    var btnflg = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { view ->
            CreateDialog()
        }
        CreateButton()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.mainactivity_menu, menu)
        return true
    }


    fun CreateButton(){
        val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
        var i = 0
        while(GLOBAL.NOTE[i] != null){
            //getLayoutInflater().inflate(R.layout.table, vg)
            getLayoutInflater().inflate(R.layout.multitable, vg)
            val tr = vg.getChildAt(i) as TableRow
            ((tr.getChildAt(0))as CheckBox).isChecked()
            ((tr.getChildAt(0))as CheckBox).setTag(i)
            ((tr.getChildAt(1)) as Button).setTag(i)
            ((tr.getChildAt(1)) as Button).setText(GLOBAL.NOTE[i])
            ((tr.getChildAt(1)) as Button).setOnClickListener {
                if(btnflg == 0){
                    //通常
                    GLOBAL.NOTE_NUMBER=Integer.parseInt(it.getTag().toString())
                    GLOBAL.PAGE_NUMBER=0
                    tap_btnWarpDisp02(it)
                }else if (btnflg == 1){
                    //編集
                    val dialog : ClsTextInputDialog = ClsTextInputDialog(this)
                    // ダイアログ用にクラスを作っているのでそこに設定している
                    dialog.dialogTitle = "編集"
                    dialog.dialogMessage = "リスト名を入力してください"
                    val tag:Int = ((tr.getChildAt(1)) as Button).getTag().toString().toInt()
                    dialog.dialogTextData = GLOBAL.NOTE[tag].toString()//testText.text.toString()
                    //ここはヒント表示に切り替える
                    dialog.onOkClickListener = DialogInterface.OnClickListener { _, _->
                        // OK選択時の処理
                        val str:String = dialog.dialogTextData
                        GLOBAL.NOTE[tag] =str
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        btnflg= 0
                        finish()
                    }
                    dialog.isCancelButton = true
                    btnflg = 0
                    // ダイアログ表示
                    dialog.openDialog(supportFragmentManager)
                }else{
                    //削除
                    GLOBAL.NOTE_NUMBER=Integer.parseInt(it.getTag().toString())
                    var num = 0
                    while(GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+num] != null){
                        GLOBAL.QUESTION[GLOBAL.NOTE_NUMBER*120+num] = null
                        GLOBAL.ANSWER[GLOBAL.NOTE_NUMBER*120+num] = null
                        num++
                    }
                    GLOBAL.NOTE[GLOBAL.NOTE_NUMBER] =null
                    var delnum =GLOBAL.NOTE_NUMBER + 1
                    if(GLOBAL.NOTE[delnum] != null){
                        while(GLOBAL.NOTE[delnum] != null){
                            val setnum = delnum -1
                            val delsoe = 0
                            while(GLOBAL.QUESTION[delnum*120+delsoe] != null) {

                            }
                            delnum++
                        }
                    }
                    btnflg = 0
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            i++
        }
    }

    fun CreateDialog(){
        val dialog : ClsTextInputDialog = ClsTextInputDialog(this)
        // ダイアログ用にクラスを作っているのでそこに設定している
        dialog.dialogTitle = "新規ノート作成"
        dialog.dialogMessage = "ノート名を入力してください"
        dialog.dialogTextData = "問題"//testText.text.toString()
        //ここはヒント表示に切り替える
        dialog.onOkClickListener = DialogInterface.OnClickListener { _, _->
            // OK選択時の処理
            GLOBAL.AddNoteName(dialog.dialogTextData)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        dialog.isCancelButton = true
        // ダイアログ表示
        dialog.openDialog(supportFragmentManager)
    }

    fun tap_btnWarpDisp02(view: View?) {
        val intent = Intent(this, Disp02::class.java)
        startActivity(intent)
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
                strBuffer+=GLOBAL.QUESTION[i*120+n]+","+GLOBAL.ANSWER[i*120+n]+","+GLOBAL.LAST[i*120+n].toString()
                if(GLOBAL.QUESTION[i*120+n+1]!=null){
                    strBuffer+="\n"
                }
            }
        }
        return strBuffer
    }

    fun tap_btnWarpDisp21(view:View?) {
        val intent = Intent(this, Disp21::class.java)
        startActivity(intent)
    }
    fun tap_btnWarpDisp08(view :View?){
        val intent= Intent(this,Disp08::class.java)
        startActivity(intent)
    }
    fun tap_btnTEST(view : View?){
        val intent= Intent(this,TEST::class.java)
        startActivity(intent)
    }

    fun FileTest(){
        val fileName1 = "$filesDir" + "/果物の漢字.csv"
        val fileName2 = "$filesDir" + "/元素記号.csv"
        val fileName3 = "$filesDir"+"/県庁所在地.csv"

        val text1     = "林檎,りんご,-1\n葡萄,ぶどう,-1\n桜桃,さくらんぼ,-1\n枇杷,びわ,-1\n檸檬,れもん,-1"
        val text2     = "1:H,水素,-1\n2:He,ヘリウム,-1\n3:Li,リチウム,-1\n4:Be,ベリリウム,-1\n5:B,ホウ素,-1\n6:C,炭素,-1\n" +
                "7:N,窒素,-1\n8:O,酸素,-1\n9:F,フッ素,-1\n10:Ne,ネオン,-1\n11:Na,ナトリウム,-1\n12:Mg,マグネシウム,-1\n13:Al,アルミニウム,-1\n" +
                "14:Si,ケイ素,-1\n15:P,リン,-1\n16:S,硫黄,-1\n17:Cl,塩素,-1\n18:Ar,アルゴン,-1\n19:K,カリウム,-1\n20:Ca,カルシウム,-1\n"
        val text3="宮城,仙台,-1\n群馬,前橋,-1\n栃木,宇都宮,-1\n茨城,水戸,-1\n福岡,福岡,-1\n沖縄,那覇,-1"

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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.End -> {
                fab.setVisibility(View.INVISIBLE)
                btnflg = 1
                //ConstraintLayout.contextでググれ
                return true
            }
            R.id.Delete -> {
                fab.setVisibility(View.INVISIBLE)
                btnflg = 2
                return true
            }
            R.id.NotSetting -> {
                val intent = Intent(this, Disp13::class.java)
                startActivity(intent)
                //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                return true
            }
            R.id.Import -> {
                FileTest()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
