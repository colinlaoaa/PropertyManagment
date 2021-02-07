package com.liao.propertymanagement.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import com.liao.propertymanagement.R
import com.liao.propertymanagement.adapters.AdapterHomeViewPager
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.ui.auth.LoginActivity
import com.liao.propertymanagement.ui.todoList.AddToDoListActivity
import com.liao.propertymanagement.ui.user_profile.ProfileActivity
import com.liao.propertymanagement.view_model.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog_home.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog_register.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog_register.bottomSheet
import kotlinx.android.synthetic.main.nav_header.view.*

class HomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        toolbar("Home")
        button_floating.setImageResource(R.drawable.ic_baseline_add_24)


        init()
    }

    private fun init() {

        bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(bottomSheet)
        button_floating.setOnClickListener {
            slideUpBottomSheet()
        }

        var adapterMyFragment = AdapterHomeViewPager(supportFragmentManager, viewModel)
        view_pager.adapter = adapterMyFragment
        tab_layout.setupWithViewPager(view_pager)

        drawerLayout = drawer_layout
        navView = nav_view
        var headView = navView.getHeaderView(0)
        headView.text_view_user_name.text = viewModel.navigationHeader()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { item ->
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
            when (item.itemId) {
                R.id.item_todo_list -> startActivity(Intent(this, AddToDoListActivity::class.java))
                R.id.item_account -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.item_logout -> {
                    viewModel.navigationLogout()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
            true
        }
    }

    private fun slideUpBottomSheet() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            button_floating.setImageResource(R.drawable.ic_baseline_remove_24)
            item_financial_summary.setOnClickListener { view_pager.currentItem = 1 }


        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED;
            button_floating.setImageResource(R.drawable.ic_baseline_add_24)
        }
    }

    override fun onResume() {
        super.onResume()
        if(!viewModel.checkLogStatus()){
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }


}