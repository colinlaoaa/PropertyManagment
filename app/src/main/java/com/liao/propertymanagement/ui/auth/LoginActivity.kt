package com.liao.propertymanagement.ui.actitvies.auth

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.R
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.ui.fragment.auth.BottomSheetFragmentRegister
import com.liao.propertymanagement.viewModel.auth.LoginViewModel
import com.liao.propertymanagement.ui.actitvies.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*


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

        login_btn.setOnClickListener {
            login_btn.startAnimation()
            var handler = Handler()
            val bitmap =
                BitmapFactory.decodeResource(this.resources, R.drawable.check)
            handler.postDelayed(
                Runnable{
                    login_btn.doneLoadingAnimation(android.R.color.white, bitmap)
                }, 2000)
            handler.postDelayed(
                Runnable{
                    login_btn.revertAnimation()
                    startActivity(Intent(this,
                        HomeActivity::class.java))
                    login_btn.background = ContextCompat.getDrawable(this, R.drawable.button_selector)
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

    override fun onResume() {
        init()
        super.onResume()
    }

}