package com.example.maindisp
import android.app.Application
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class MyApp :Application(){


    var QUESTION=Array<String?>(2400,{null})//問題文
    var ANSWER=Array<String?>(2400,{null})//回答文
    var LAST=Array<Int?>(2400,{null})//前回正誤
    var NOTE=Array<String?>(20,{null})//ノートのタイトル
    var PAGE_NUMBER:Int=0//ページ番号管理用 0-119
    var NOTE_NUMBER:Int=0//ノート番号管理用 0-19
    var TIMESPAN:Int=5//通知間隔設定用


    //吉田テスト
    var FLG:Boolean = false


    //開始時処理
    override fun onCreate() {
        super.onCreate()
        READFILE()
        val str="--------------------------------------------------------------------->$filesDir"
    }


    fun READFILE(){
        val GLOBAL=MyApp.getInstance()
        try{
            var list=File("$filesDir").list()
            var str:String=""

            for(i in list.indices){
                var EX=getExtention(list[i])
                //拡張子がcsvのタイトルを取得し、ノートに追加
                if(EX=="csv"){
                    AddNoteName(hideExtention(list[i]))
                }
            }
            for(i in GLOBAL.NOTE.indices){
                if(GLOBAL.NOTE[i]!=null){
                    getFileData(GLOBAL.NOTE[i],i)
                }
            }

        }catch(e:Exception){

        }
    }

    fun getFileData(f_name:String?,n:Int){
        val GLOBAL=MyApp.getInstance()
        try{
            val file=File("/",f_name+".csv")
            val scan=Scanner(file)
            scan.useDelimiter(",|\n")
            var i:Int=0
            while(scan.hasNext()){
                GLOBAL.QUESTION[i+n*120]=scan.next()
                GLOBAL.ANSWER[i+n*120]=scan.next()
                GLOBAL.LAST[i+n*120]=scan.nextInt()
                i+=1
            }
        }catch(e:Exception){

        }
    }


    //引数:ファイル名 戻り値:拡張子（ドット含まず）
    fun getExtention(str:String):String{
        var point:Int=str.lastIndexOf(".")
        if(point!=-1){
            return str.substring(point+1)
        }
        return ""
    }

    //引数:ファイル名 戻り値:ファイル名（拡張子含まず）
    fun hideExtention(str:String):String{
        var point:Int=str.lastIndexOf(".")
        if(point!=-1){
            return str.substring(0,point)
        }
        return ""
    }

    //引数:ファイル名 ノート名最後にファイルを追加します
    fun AddNoteName(str:String){
        val GLOBAL=MyApp.getInstance()
        for(i in 0..19){
            if(GLOBAL.NOTE[i]==null){
                GLOBAL.NOTE[i]=str
                break
            }
        }
    }


    companion object {
        private var instance : MyApp? = null
        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()
            return instance!!
        }
    }
}