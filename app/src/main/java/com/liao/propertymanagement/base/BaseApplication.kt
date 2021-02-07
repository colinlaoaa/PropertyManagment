package com.liao.propertymanagement.base

import android.app.Application
import android.util.Log
import com.liao.propertymanagement.helper.SessionManager

class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        SessionManager.init(this.applicationContext)
        Log.d("1111","On create")
    }
}