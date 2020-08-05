package com.liao.propertymanagement.ui.auth.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.liao.propertymanagement.R
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity(), View.OnClickListener {
    private val KEY = "key"
    private val LANDLORD = "landlord"
    private val TENANTS = "tenants"

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
        when (view) {
            button_login -> startActivity(Intent(this, LoginActivity::class.java))
            button_register_landlord -> {
                myIntent.putExtra(KEY, LANDLORD)
                startActivity(myIntent)
            }
            button_register_tenants -> {
                myIntent.putExtra(KEY, TENANTS)
                startActivity(myIntent)
            }
        }
    }
}