package com.example.maindisp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.io.File
import java.util.*

class Alarm : AppCompatActivity(){

    val GLOBAL = MyApp.getInstance()

    var QUESTION=Array<String?>(2400,{null})//問題文
    var ANSWER=Array<String?>(2400,{null})//回答文
    var LAST=Array<Int?>(2400,{null})//前回正誤
    var NOTE=Array<String?>(20,{null})//ノートのタイトル


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        READFILE()
        testNotification()
        if(GLOBAL.TIMESPAN>0){
            setAlarmManager()
        }
        finish()
    }
    fun setAlarmManager(){


        val file=File("$filesDir/TIMESPAN.txt")
        val scan=Scanner(file)

        if(scan.hasNext())GLOBAL.TIMESPAN=scan.nextInt()
        else GLOBAL.TIMESPAN=0

        val calendar= Calendar.getInstance()
        calendar.timeInMillis=System.currentTimeMillis()
        calendar.add(Calendar.MINUTE,GLOBAL.TIMESPAN)
        val am=getSystemService(Context.ALARM_SERVICE)as AlarmManager
        val intent= Intent(this,Receiver::class.java)
        val pending= PendingIntent.getBroadcast(this,0,intent,0)
        when{
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP->{
                val info= AlarmManager.AlarmClockInfo(calendar.timeInMillis,null)
                am.setAlarmClock(info,pending)
            }
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT->{
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
            }
            else->{
                am.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
            }
        }
    }



    fun onNotification(strTitle:String,strText:String){
        val channelId = "CHANNEL_ID"
        val builder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle(strTitle)
            setContentText(strText)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        // API 26 以上の場合は NotificationChannel に登録する
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "CHANNEL_NAME"
            val description = "SAMPLE"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                this.description = description
            }

            // システムにチャンネルを登録する
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(this)) {
            notify(1234567, builder.build())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun testNotification(){
        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        val name="通知のタイトル"
        val id="unique ID"
        val notifyDescription="通知の詳細情報"
        val Nnum=(0..NOTENUM()).random()
        val Qnum=(0..QUESTIONNUM(Nnum)).random()
        if (notificationManager.getNotificationChannel(id) == null) {
            val mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            mChannel.apply {
                description = notifyDescription
            }
            notificationManager.createNotificationChannel(mChannel)
        }
        val notification = NotificationCompat
            .Builder(this, id)
            .apply {
                setSmallIcon(R.drawable.ic_launcher_background)
                setContentTitle(QUESTION[Nnum*120+Qnum])
                setContentText(ANSWER[Nnum*120+Qnum])
            }.build()
        notificationManager.notify(1, notification)
    }


    fun NOTENUM():Int{
        var n=0
        while(NOTE[n]!=null){
            n++
        }
        return n-1
    }
    fun QUESTIONNUM(Nnum:Int):Int{
        var n=0
        while(QUESTION[Nnum*120+n]!=null){
            n++
        }
        return n-1
    }


    fun READFILE(){
        try{
            var list= File("$filesDir").list()
            var str:String=""

            for(i in list.indices){
                var EX=getExtention(list[i])
                //拡張子がcsvのタイトルを取得し、ノートに追加
                if(EX=="csv"){
                    AddNoteName(hideExtention(list[i]))
                }
            }
            for(i in NOTE.indices){
                if(NOTE[i]!=null){
                    getFileData(NOTE[i],i)
                }
            }

        }catch(e:Exception){

        }
    }

    fun getFileData(f_name:String?,n:Int){
        try{
            val file= File("$filesDir/",f_name+".csv")
            val scan=Scanner(file)
            scan.useDelimiter(",|\n")
            var i:Int=0
            while(scan.hasNext()){
                QUESTION[i+n*120]=scan.next()
                ANSWER[i+n*120]=scan.next()
                LAST[i+n*120]=scan.nextInt()
                i+=1
            }
        }catch(e:Exception){
            print(e)
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
        for(i in 0..19){
            if(NOTE[i]==null){
                NOTE[i]=str
                break
            }
        }
    }


}