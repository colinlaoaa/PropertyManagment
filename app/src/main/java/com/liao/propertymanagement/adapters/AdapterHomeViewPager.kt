package com.liao.propertymanagement.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.ui.home.FinacialSummaryFragment
import com.liao.propertymanagement.ui.home.LandlordHomeFragment
import com.liao.propertymanagement.ui.home.TenantsHomeFragment
import com.liao.propertymanagement.view_model.home.HomeViewModel


class AdapterHomeViewPager(fm: FragmentManager,private var viewModel: HomeViewModel) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return if (viewModel.isLandlord()) {
            when (position) {
                0 -> LandlordHomeFragment()
                else -> FinacialSummaryFragment()
            }
        } else {
            when (position) {
                0 -> TenantsHomeFragment()
                else -> FinacialSummaryFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (viewModel.isLandlord()) {
            when (position) {
                0 -> "Landlord"
                else -> "FinancialSummary"

            }
        } else {
            when (position) {
                0 -> "Tenants"
                else -> "FinancialSummary"

            }
        }
    }

}