package com.liao.propertymanagement.ui.actitvies.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.liao.propertymanagement.R
import com.liao.propertymanagement.ui.actitvies.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private val delayedTime: Long = 2200
    private val isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        var handler = Handler()
        handler.postDelayed({
            checkLogin()
        }, delayedTime)
    }

    private fun checkLogin() {
        var intent = if(isLoggedIn){
            Intent(this, HomeActivity::class.java)
        }else{
            Intent(this, NavActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}