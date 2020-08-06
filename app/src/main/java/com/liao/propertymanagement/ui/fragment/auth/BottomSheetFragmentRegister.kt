package com.liao.propertymanagement.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.liao.propertymanagement.R
import com.liao.propertymanagement.ui.actitvies.auth.RegisterActivity
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog_register.*


class BottomSheetFragmentRegister():BottomSheetDialogFragment() {

    private var fragmentView: View? = null
    private val KEY = "key"
    private val LANDLORD = "landlord"
    private val TENANTS = "tenants"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_bottom_sheet_dialog_register, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }



    private fun initView() {
        var myIntent =Intent(activity,
            RegisterActivity::class.java)
        item_Landlord.setOnClickListener {
            myIntent.putExtra(KEY,LANDLORD)
            startActivity(myIntent)
        }
        item_tenants.setOnClickListener {
            myIntent.putExtra(KEY,TENANTS)
            startActivity(myIntent)
        }
    }
}