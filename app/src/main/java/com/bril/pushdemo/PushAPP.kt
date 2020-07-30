package com.bril.pushdemo

import android.app.Application
import android.util.Log
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.MsgConstant

import com.umeng.message.PushAgent


/**
 * ***********************************
 *
 * @项目名称: pushDemo
 * @Author KK
 * @邮箱: 13263181110@163.com
 * @创建时间: 2020/7/30 10:41
 * 用途:
 * ***********************************
 */
class PushAPP : Application() {
    companion object {
        private const val TAG = "PushAPP"
    }

    override fun onCreate() {
        super.onCreate()
        UMConfigure.init(
            this,
            "5f222fceb4b08b653e8f77d5",
            "Umeng",
            UMConfigure.DEVICE_TYPE_PHONE,
            "6c7b7951c14184f66a5166c0e06d3b07"
        )
        UMConfigure.setLogEnabled(true)
        val instance = PushAgent.getInstance(this)
        instance.messageHandler = UmengMessage()
        instance.notificationClickHandler = NotificationClick()
        instance.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i(Companion.TAG, "注册成功：deviceToken：-------->  $deviceToken")
            }

            override fun onFailure(s: String, s1: String) {
                Log.e(Companion.TAG, "注册失败：-------->  s:$s,s1:$s1")
            }
        })
    }
}