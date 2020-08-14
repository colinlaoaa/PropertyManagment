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
import com.liao.propertymanagement.databinding.ActivityRegisterBinding
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.model.Register
import com.liao.propertymanagement.view_model.auth.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val KEY = "key"
    private val LANDLORD = "landlord"
    private val TENANTS = "tenants"
    private lateinit var binding: ActivityRegisterBinding
    private var registerInfo: Register? = null
    private var handler = Handler()
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        toolbar("Register")
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        observerData()

        init()
    }

    private fun observerData() {
        viewModel.registerRepositoryObserver().observe(this, Observer {
            registerInfo = it
            if (registerInfo?.error == false) {
                register_btn.startAnimation()
                val bitmap =
                    BitmapFactory.decodeResource(this.resources, R.drawable.check)
                handler.postDelayed(
                    Runnable {
                        register_btn.doneLoadingAnimation(android.R.color.white, bitmap)
                    }, 2000
                )
                handler.postDelayed(
                    Runnable {
                        register_btn.revertAnimation()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }, 4000
                )

                register_btn.background =
                    ContextCompat.getDrawable(this, R.drawable.button_selector)
            } else {
                register_btn.startAnimation()
                val bitmap =
                    BitmapFactory.decodeResource(this.resources, R.drawable.error)
                handler.postDelayed(
                    Runnable {
                        register_btn.doneLoadingAnimation(android.R.color.white, bitmap)
                        var snack = Snackbar.make(
                            register_btn,
                            "incorrect information",
                            Snackbar.LENGTH_LONG
                        )
                        snack.show()
                    }, 2000
                )
                handler.postDelayed(
                    Runnable {
                        register_btn.revertAnimation()

                    }, 4000
                )
                register_btn.background =
                    ContextCompat.getDrawable(this, R.drawable.button_selector)

            }
        }
        )
    }

    private fun init() {

        binding.registerInputLandlordEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                del_landlord_email_btn.visibility = View.GONE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    del_landlord_email_btn.visibility = View.GONE
                } else {
                    del_landlord_email_btn.visibility = View.VISIBLE
                    del_landlord_email_btn.setOnClickListener {
                        viewModel.cleanLandlordEmailEditText()
                    }
                }
            }

        })


        binding.registerInputUsername.addTextChangedListener(object : TextWatcher {
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
                        viewModel.cleanNameEditText()
                    }
                }
            }

        })

        binding.registerInputEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                del_useremail_btn.visibility = View.GONE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    del_useremail_btn.visibility = View.GONE
                } else {
                    del_useremail_btn.visibility = View.VISIBLE
                    del_useremail_btn.setOnClickListener {
                        viewModel.cleanEmailEditText()
                    }
                }
            }

        })


        binding.registerInputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                del_passwd_btn1.visibility = View.GONE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    del_passwd_btn1.visibility = View.GONE
                    passwd_eye_btn1.visibility = View.GONE
                    binding.registerInputPassword.inputType =  InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                } else {
                    del_passwd_btn1.visibility = View.VISIBLE
                    passwd_eye_btn1.visibility = View.VISIBLE
                    del_passwd_btn1.setOnClickListener {
                        viewModel.cleanPasswordEditText()
                    }
                    passwd_eye_btn1.setOnClickListener {
                        binding.registerInputPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

                    }

                }
            }

        })



        if (intent.getStringExtra(KEY) == LANDLORD) {
            check_box_landlord.isChecked = true
            register_input_landlord_email.isEnabled = false
        }
        if (intent.getStringExtra(KEY) == TENANTS) {
            //conflict with data binding bug here
            //binding.checkBoxTenant.isChecked = true
            viewModel.setCheckBox()

        }
        check_box_tenant.setOnClickListener {
            check_box_landlord.isChecked = !check_box_tenant.isChecked
            check_box_tenant.isChecked = !check_box_landlord.isChecked
            register_input_landlord_email.isEnabled = check_box_tenant.isChecked
            register_input_landlord_email.text = null
        }

        check_box_landlord.setOnClickListener {
            check_box_tenant.isChecked = !check_box_landlord.isChecked
            check_box_landlord.isChecked = !check_box_tenant.isChecked
            register_input_landlord_email.isEnabled = !check_box_landlord.isChecked
            register_input_landlord_email.text = null
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.login_menu -> startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
            android.R.id.home -> finish()

        }
        return super.onOptionsItemSelected(item)
    }
}