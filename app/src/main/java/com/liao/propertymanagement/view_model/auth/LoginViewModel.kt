package com.liao.propertymanagement.view_model.auth

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.api.ApiClient
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.User

class LoginViewModel : ViewModel() {
    val email = ObservableField<String?>()
    val password = ObservableField<String?>()
    private val loginRepository =
        LoginRepository(ApiClient.getApiEndPoint())


    fun loginRepositoryObserver() = loginRepository.loginResponse

    private fun giveParamsGetList(user: User) {
        loginRepository.postLoginInfo(user)
    }

    fun loginButtonClicked() {
        var user = User()
        user.email = email.get()
        user.password = password.get()
        giveParamsGetList(user)
    }

    fun cleanEmailEditText() {
        email.set("")
    }

    fun cleanPasswordEditText() {
        password.set("")
    }

    fun rememberAccount() {
        email.set(SessionManager.getLandlordEmail())
    }


}