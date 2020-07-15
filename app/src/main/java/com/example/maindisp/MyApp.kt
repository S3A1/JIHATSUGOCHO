package com.example.maindisp
import android.app.Application
class MyApp :Application(){

    var QUESTION=Array<String?>(2400,{null})//問題文
    var ANSWER=Array<String?>(2400,{null})//回答文
    var NOTE=Array<String?>(20,{null})//ノートのタイトル
    var PAGE_NUMBER:Int=0//ページ番号管理用 0-119
    var NOTE_NUMBER:Int=0//ノート番号管理用 0-19

    val GLOBAL=MyApp.getInstance()

   /* override fun onCreate() {
        super.onCreate()

        GLOBAL.QUESTION[0]="Q1"
        GLOBAL.ANSWER[0]="A1"
        GLOBAL.QUESTION[1]="Q2"
        GLOBAL.ANSWER[1]="A2"
        GLOBAL.QUESTION[2]="Q3"
        GLOBAL.ANSWER[2]="A3"
        GLOBAL.NOTE_NUMBER=0;
        GLOBAL.PAGE_NUMBER=0;
        GLOBAL.NOTE[0]="作成テスト"

    }*/


    companion object {
        private var instance : MyApp? = null
        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()
            return instance!!
        }
    }
}