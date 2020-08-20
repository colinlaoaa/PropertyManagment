package com.liao.propertymanagement.ui.tenants_management

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.liao.propertymanagement.R
import com.liao.propertymanagement.helper.toolbar

class TenantsAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenants_all)

        toolbar("Tenants List")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.add_menu_bar -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,
                    TenantsAddFragment()
                ).addToBackStack("").commit()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}