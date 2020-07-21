package com.example.maindisp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class Alarm : AppCompatActivity(){

    val GLOBAL = MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onNotification("テスト","本文")
        if(GLOBAL.TIMESPAN!=-1){
            setAlarmManager()
        }
        finish()
    }
    fun setAlarmManager(){
        val calendar= Calendar.getInstance()
        calendar.timeInMillis=System.currentTimeMillis()
        calendar.add(Calendar.SECOND,GLOBAL.TIMESPAN)
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


}