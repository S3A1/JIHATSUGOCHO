package com.example.maindisp
import android.app.Application
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class MyApp :Application(){


    var QUESTION=Array<String?>(2400,{null})//問題文
    var ANSWER=Array<String?>(2400,{null})//回答文
    var NOTE=Array<String?>(20,{null})//ノートのタイトル
    var PAGE_NUMBER:Int=0//ページ番号管理用 0-119
    var NOTE_NUMBER:Int=0//ノート番号管理用 0-19

    override fun onCreate() {

        super.onCreate()

        val GLOBAL=MyApp.getInstance()

        GLOBAL.QUESTION[0]="Q1"
        GLOBAL.ANSWER[0]="A1"
        GLOBAL.QUESTION[1]="Q2"
        GLOBAL.ANSWER[1]="A2"
        GLOBAL.QUESTION[2]="Q3"
        GLOBAL.ANSWER[2]="A3"
        GLOBAL.NOTE_NUMBER=0;
        GLOBAL.PAGE_NUMBER=0;
        GLOBAL.NOTE[0]="作成テスト"

        //ここにファイルから読み込む処理を記述

        READFILE()
    }

    fun READFILE(){
        try{
            var list=File("$filesDir").list()
            var str:String=""

            for(i in list.indices){
                var EX=getExtention(list[i])
                //拡張子がcsvのタイトルを取得し、ノートに追加
                if(EX=="csv"){
                    AddNoteName(hideExtention(list[i]))
                    getQuestion(list[i])
                }
            }

        }catch(e:Exception){

        }
    }

    fun getQuestion(f_name:String){
        val GLOBAL=MyApp.getInstance()
        try{
            val file=File("$filesDir/"+f_name)
            val scan=Scanner(file)
            scan.useDelimiter(",|\n")
            var i:Int=0
            while(scan.hasNext()){
                GLOBAL.QUESTION[i]=scan.next()
                GLOBAL.ANSWER[i]=scan.next()
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