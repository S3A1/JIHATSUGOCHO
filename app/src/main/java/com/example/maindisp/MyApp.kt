package com.example.maindisp
import android.app.Application
class MyApp :Application(){

    var QUESTION=Array<String?>(2400,{null})
    var ANSWER=Array<String?>(2400,{null})
    var NOTE=Array<String?>(20,{null})



    companion object {
        private var instance : MyApp? = null
        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()
            return instance!!
        }
    }
}