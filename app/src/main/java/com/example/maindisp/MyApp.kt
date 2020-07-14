package com.example.maindisp
import android.app.Application
class MyApp :Application(){

    var QUESTION=Array<String?>(2400,{null})//問題文
    var ANSWER=Array<String?>(2400,{null})//回答文
    var NOTE=Array<String?>(20,{null})//ノートのタイトル
    var PAGE_NUMBER:Int=0//ページ番号管理用 0-119
    var NOTE_NUMBER:Int=0//ノート番号管理用 0-19



    companion object {
        private var instance : MyApp? = null
        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()
            return instance!!
        }
    }
}