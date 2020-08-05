package com.liao.propertymanagement.ui.auth.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.R
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.ui.auth.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val KEY = "key"
    private val LANDLORD = "landlord"
    private val TENANTS = "tenants"
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        toolbar("Register")
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        init()
    }

    private fun init() {
        if (intent.getStringExtra(KEY) == LANDLORD) {
            check_box_landlord.isChecked = true
        }
        if (intent.getStringExtra(KEY) == TENANTS) {
            check_box_tenant.isChecked = true
            login_input_lanlord_email.isEnabled = false
        }
        check_box_tenant.setOnClickListener {
            check_box_landlord.isChecked = !check_box_tenant.isChecked
            check_box_tenant.isChecked = !check_box_landlord.isChecked
            login_input_lanlord_email.isEnabled = !check_box_tenant.isChecked
        }

        check_box_landlord.setOnClickListener {
            check_box_tenant.isChecked = !check_box_landlord.isChecked
            check_box_landlord.isChecked = !check_box_tenant.isChecked
            login_input_lanlord_email.isEnabled = check_box_landlord.isChecked
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar_login,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.login_menu -> startActivity(Intent(this,LoginActivity::class.java))
            android.R.id.home ->finish()

        }
        return super.onOptionsItemSelected(item)
    }
}