package com.liao.propertymanagement.view_model.home

import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.helper.SessionManager

class HomeViewModel : ViewModel() {
    fun isLandlord(): Boolean {
        return SessionManager.isLandlord()
    }

    fun navigationHeader(): String? {
        return SessionManager.getName()
    }

    fun navigationLogout() {
        SessionManager.logout()
    }

    fun checkLogStatus(): Boolean {
        return SessionManager.checkLogin()
    }


}