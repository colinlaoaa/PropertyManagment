package com.liao.propertymanagement.ui.actitvies.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.liao.propertymanagement.R
import com.liao.propertymanagement.helper.toolbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog_register.*

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
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}