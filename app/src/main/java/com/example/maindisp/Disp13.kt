package com.example.maindisp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class Disp13 : AppCompatActivity() {

    val GLOBAL = MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disp13)
        //val file=File("$filesDir/TIMESPAN.txt")
        //if(file!=null) {
        //    val scan = Scanner(file)
        //    if (scan.hasNext()) GLOBAL.TIMESPAN = scan.nextInt()
        //    else GLOBAL.TIMESPAN = 0
        //}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.disp13_menu, menu)
        return true
    }

    fun CreateDialog(){
        val timeList=arrayOf("なし","10分毎","15分毎","30分毎",
            "1時間毎","3時間毎","6時間毎","12時間毎","24時間毎")
        AlertDialog.Builder(this)
            .setTitle("通知間隔を設定してください")
            .setItems(timeList,{dialog,which->SetTimeSpan(timeList[which])}).show()
    }

    fun SetTimeSpan(strTime:String){
        when(strTime){
            "なし"->{
                WriteTimeSpan(0)
            }
            "10分毎"->{
                WriteTimeSpan(10)
            }
            "15分毎"->{
                WriteTimeSpan(15)
            }
            "30分毎"->{
                WriteTimeSpan(30)
            }
            "1時間毎"->{
                WriteTimeSpan(60)
            }
            "3時間毎"->{
                WriteTimeSpan(180)
            }
            "6時間毎"->{
                WriteTimeSpan(360)
            }
            "12時間毎"->{
                WriteTimeSpan(720)
            }
            "24時間毎"->{
                WriteTimeSpan(1440)
            }
        }
    }

    fun WriteTimeSpan(MIN:Int){
        val fileName = "$filesDir" + "/TIMESPAN.txt"
        val text     = MIN.toString()
        try{
            val writeFile = File(fileName)
            writeFile.writeText(text)
        } catch (e: FileNotFoundException){
            println(e)
        }
    }

    fun setAlarmManager(){
        val calendar= Calendar.getInstance()
        calendar.timeInMillis=System.currentTimeMillis()
        calendar.add(Calendar.SECOND,5)
        val am=getSystemService(Context.ALARM_SERVICE)as AlarmManager
        val intent= Intent(this,Receiver::class.java)
        val pending= PendingIntent.getBroadcast(this,0,intent,0)
        when{
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP->{
                val info=AlarmManager.AlarmClockInfo(calendar.timeInMillis,null)
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

    fun tap_btnNotSetting(view : View?){
        CreateDialog()
    }
    fun tap_btnNotStart(view : View?){
        if(GLOBAL.TIMESPAN>0)setAlarmManager()
    }
    fun tap_btnNotEnd(view :View?){
        val am=getSystemService(Context.ALARM_SERVICE)as AlarmManager
        val intent = Intent(this,Receiver::class.java)
        val pending= PendingIntent.getBroadcast(this,0,intent,0)
        am.cancel(pending)
    }
    fun tap_btnHome(view :View?){
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.Home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}