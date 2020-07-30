package com.bril.pushdemo

import android.content.Context
import android.widget.Toast
import com.umeng.message.UmengNotificationClickHandler
import com.umeng.message.entity.UMessage

/**
 ************************************
 *
 * @项目名称: pushDemo
 * @Author KK
 * @邮箱: 13263181110@163.com
 * @创建时间: 2020/7/30 11:26
 * 用途:
 ************************************
 */
class NotificationClick : UmengNotificationClickHandler() {
    override fun dealWithCustomAction(context: Context?, msg: UMessage?) {
        Toast.makeText(context, msg?.custom, Toast.LENGTH_LONG).show();
    }
}