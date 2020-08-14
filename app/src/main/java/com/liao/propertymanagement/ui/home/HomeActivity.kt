package com.liao.propertymanagement.ui.actitvies.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.liao.propertymanagement.R
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.ui.actitvies.properties.PropertiesActivity
import com.liao.propertymanagement.ui.actitvies.todoList.AddToDoListActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog_register.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar("Home")
        button_floating.setImageResource(R.drawable.ic_baseline_add_24)

        init()
    }

    private fun init() {
        bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(bottomSheet)
        button_floating.setOnClickListener {
            slideUpBottomSheet()
        }
        image_view_todo_list.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddToDoListActivity::class.java
                )
            )
        }
        image_view_todo_list_t.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddToDoListActivity::class.java
                )
            )
        }

        image_view_properties.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PropertiesActivity::class.java
                )
            )
        }

    }

    private fun slideUpBottomSheet() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            button_floating.setImageResource(R.drawable.ic_baseline_remove_24)

        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED;
            button_floating.setImageResource(R.drawable.ic_baseline_add_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}