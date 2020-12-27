package com.kailaisii.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var notificationId = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener { sendNotifiCation("这是一条通知栏消息") }
    }

    /**
     * Notification通知
     */
    protected fun sendNotifiCation(alarmContent: String) {
        //点击发送广播，然后在广播中打开应用
        val intent = Intent(this, NotificationReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val channelId: String = createNotificationChannel(
            "channel_ID",
            "异常消息推送通知",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("消息栏通知")//标题栏
            .setContentText("这是一条很长很长的消息栏通知详情。")//详情
            .setContentIntent(pendingIntent)//设置意图，比如说跳转到某个页面等等
            .setSmallIcon(R.drawable.notification_logo)//小图标
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.notification_logo))//大图标。如果有大图标，则大图标在右侧小图标在左侧。如果没有大图标，则只在左侧显示小图标。
            .setPriority(NotificationCompat.PRIORITY_HIGH)//优先级
            .setFullScreenIntent(pendingIntent, true)//锁屏通知下的通知
            .setCategory(Notification.CATEGORY_CALL)
            .setColor(resources.getColor(android.R.color.holo_red_light))
            .setNumber(0)//显示的角标数字
            .setAutoCancel(true)//用户点击面板的时候是否自动取消
            .setDefaults(Notification.DEFAULT_ALL)//设置通知的默认效果。即设置和系统保持一致。可以设置ALL，Notification#DEFAULT_SOUND、link Notification#DEFAULT_VIBRATE、Notification#DEFAULT_LIGHTS、Notification#DEFAULT_ALL
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId++, notification.build())
    }

    private fun createNotificationChannel(channelID: String, channelNAME: String, level: Int): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(channelID, channelNAME, level)
            channel.setShowBadge(true)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)
            channelID
        } else {
            ""
        }
    }
}