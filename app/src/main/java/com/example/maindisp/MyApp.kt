package com.example.maindisp
import android.app.Application
class MyApp :Application(){


    val QUESTION=Array<String?>(2400,{null})
    val ANSWER=Array<String?>(2400,{null})
    val NOTE=Array<String?>(20,{null})

    companion object {
        private var instance : MyApp? = null
        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()
            return instance!!
        }
    }
}