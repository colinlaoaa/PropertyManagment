package com.liao.propertymanagement.ui.actitvies.auth

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.content.ContextCompat
import com.liao.propertymanagement.R
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity(), View.OnClickListener {
    private val KEY = "key"
    private val LANDLORD = "landlord"
    private val TENANTS = "tenants"
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        init()
    }

    private fun init() {
        button_login.setOnClickListener(this)
        button_register_landlord.setOnClickListener(this)
        button_register_tenants.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var myIntent = Intent(this, RegisterActivity::class.java)
        val bitmap: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.check)
        when (view) {

            button_login -> {
                button_login.startAnimation()
                handler.postDelayed({
                    button_login.doneLoadingAnimation(android.R.color.white, bitmap)
                }, 2000)

                handler.postDelayed({
                    button_login.revertAnimation()
                    startActivity(Intent(this, LoginActivity::class.java))
                    button_login.background = ContextCompat.getDrawable(this, R.drawable.button_selector)
                }, 4000)
               }
            button_register_landlord -> {
                button_register_landlord.startAnimation()
                handler.postDelayed({
                    button_register_landlord.doneLoadingAnimation(android.R.color.white, bitmap)
                }, 2000)

                handler.postDelayed({
                    button_register_landlord.revertAnimation()
                    myIntent.putExtra(KEY, LANDLORD)
                    startActivity(myIntent)
                    button_register_landlord.background = ContextCompat.getDrawable(this, R.drawable.button_selector)
                }, 4000)
            }
            button_register_tenants -> {
                button_register_tenants.startAnimation()
                handler.postDelayed({
                    button_register_tenants.doneLoadingAnimation(android.R.color.white, bitmap)
                }, 2000)

                handler.postDelayed({
                    button_register_tenants.revertAnimation()
                    myIntent.putExtra(KEY, TENANTS)
                    startActivity(myIntent)
                    button_register_tenants.background = ContextCompat.getDrawable(this, R.drawable.button_selector)
                }, 4000)
            }
        }
    }
}