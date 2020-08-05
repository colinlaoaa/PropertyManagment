package com.liao.propertymanagement.ui.auth.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.liao.propertymanagement.R
import com.liao.propertymanagement.ui.auth.fragment.BottomSheetFragmentRegister
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.ui.auth.viewModel.LoginViewModel
import com.liao.propertymanagement.ui.home.activities.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_nav.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var bottomSheetFragment: BottomSheetFragmentRegister
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        toolbar("Login")

        init()
    }

    private fun init() {
        bottomSheetFragment =
            BottomSheetFragmentRegister()

        var  btn = findViewById<CircularProgressButton>(R.id.login_btn)



        login_btn.setOnClickListener {
            btn.startAnimation()
            var handler = Handler()
            handler.postDelayed(
                Runnable{
                    startActivity(Intent(this,HomeActivity::class.java))

                }, 3000)
             }
    }

    private fun showBottomSheetDialog() {
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar_register,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.register_menu -> showBottomSheetDialog()
            android.R.id.home ->finish()
        }
        return super.onOptionsItemSelected(item)
    }

}