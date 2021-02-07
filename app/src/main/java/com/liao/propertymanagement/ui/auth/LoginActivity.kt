package com.liao.propertymanagement.ui.auth

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.liao.propertymanagement.R
import com.liao.propertymanagement.databinding.ActivityLoginBinding
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.model.Login
import com.liao.propertymanagement.ui.home.HomeActivity
import com.liao.propertymanagement.view_model.auth.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var bottomSheetFragment: BottomSheetFragmentRegister
    private var loginInfo: Login? = null
    private var handler = Handler()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        toolbar("Login")

        observerData()

        init()
    }

    private fun observerData() {
        viewModel.loginRepositoryObserver().observe(this, Observer {
            loginInfo = it

            if (loginInfo?.token != null) {
                login_btn.startAnimation()
                val bitmap =
                    BitmapFactory.decodeResource(this.resources, R.drawable.check)
                handler.postDelayed(
                    Runnable {
                        login_btn.doneLoadingAnimation(android.R.color.white, bitmap)
                    }, 2000
                )
                handler.postDelayed(
                    Runnable {
                        login_btn.revertAnimation()
                        startActivity(
                            Intent(
                                this,
                                HomeActivity::class.java
                            )
                        )
                    }, 4000
                )
                login_btn.background =
                    ContextCompat.getDrawable(this, R.drawable.button_selector)
            } else {
                login_btn.startAnimation()
                val bitmap =
                    BitmapFactory.decodeResource(this.resources, R.drawable.error)
                handler.postDelayed(
                    Runnable {
                        login_btn.doneLoadingAnimation(android.R.color.white, bitmap)
                        var snack = Snackbar.make(
                            login_btn,
                            "incorrect email or password",
                            Snackbar.LENGTH_LONG
                        )
                        snack.show()
                    }, 2000
                )
                handler.postDelayed(
                    Runnable {
                        login_btn.revertAnimation()

                    }, 4000
                )
                login_btn.background =
                    ContextCompat.getDrawable(this, R.drawable.button_selector)
            }

        })
    }

    private fun init() {

        binding.rememberPwd.setOnClickListener {
            viewModel.rememberAccount()
        }
        binding.loginInputUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                del_username_btn.visibility = View.GONE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    del_username_btn.visibility = View.GONE
                } else {
                    del_username_btn.visibility = View.VISIBLE
                    del_username_btn.setOnClickListener {
                        viewModel.cleanEmailEditText()
                    }
                }
            }

        })

        binding.loginInputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                del_passwd_btn.visibility = View.GONE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    del_passwd_btn.visibility = View.GONE
                    passwd_eye_btn.visibility = View.GONE
                    binding.loginInputPassword.inputType =  InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                } else {
                    del_passwd_btn.visibility = View.VISIBLE
                    passwd_eye_btn.visibility = View.VISIBLE
                    del_passwd_btn.setOnClickListener {
                        viewModel.cleanPasswordEditText()
                    }
                    passwd_eye_btn.setOnClickListener {
                        binding.loginInputPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

                    }

                }
            }

        })


        bottomSheetFragment =
            BottomSheetFragmentRegister()
    }

    private fun showBottomSheetDialog() {
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar_register, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.register_menu -> showBottomSheetDialog()
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        init()
        super.onResume()
    }

}