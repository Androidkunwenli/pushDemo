package com.bril.pushdemo

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.umeng.message.UmengMessageHandler
import com.umeng.message.entity.UMessage


/**
 * ***********************************
 *
 * @项目名称: pushDemo
 * @Author KK
 * @邮箱: 13263181110@163.com
 * @创建时间: 2020/7/30 11:11
 * 用途:
 * ***********************************
 */
class UmengMessage : UmengMessageHandler() {
    override fun getNotification(
        context: Context,
        msg: UMessage
    ): Notification? {
        Log.d("消息", msg.title + "---" + msg.text)
        return when (msg.builder_id) {
            1 -> {
                val myNotificationView = RemoteViews(
                    context.packageName,
                    R.layout.notification_view
                )
                myNotificationView.setTextViewText(R.id.notification_title, msg.title)
                myNotificationView.setTextViewText(R.id.notification_text, msg.text)
                myNotificationView.setImageViewBitmap(
                    R.id.notification_large_icon,
                    getLargeIcon(context, msg)
                )
                myNotificationView.setImageViewResource(
                    R.id.notification_small_icon,
                    getSmallIconId(context, msg)
                )
                val builder: Notification?
                val systemService =
                    context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationChannel = NotificationChannel(
                        "upush_default",
                        "我是渠道名字",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    systemService.createNotificationChannel(notificationChannel)
                    builder = Notification.Builder(context, "upush_default")
                        .setContent(myNotificationView)
                        .setSmallIcon(getSmallIconId(context, msg))
                        .setTicker(msg.ticker)
                        .setAutoCancel(true)
                        .build()
                } else {
                    builder = NotificationCompat.Builder(context)
                        .setContent(myNotificationView)
                        .setSmallIcon(getSmallIconId(context, msg))
                        .setTicker(msg.ticker)
                        .setAutoCancel(true)
                        .build()
                }
                return builder
            }
            else ->                 //默认为0，若填写的builder_id并不存在，也使用默认。
                super.getNotification(context, msg)
        }
    }
}