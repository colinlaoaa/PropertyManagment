package com.liao.propertymanagement.viewModel.auth

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.api.ApiClient
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

    fun loginButtonClicked(){
        var user = User()
        user.email = email.get()
        user.password = password.get()
        giveParamsGetList(user)
    }


}