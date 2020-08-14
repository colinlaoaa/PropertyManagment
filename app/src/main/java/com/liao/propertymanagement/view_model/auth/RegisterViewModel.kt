package com.liao.propertymanagement.viewModel.auth

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.api.ApiClient
import com.liao.propertymanagement.model.User


class RegisterViewModel : ViewModel() {
    private val registerRepository =
        RegisterRepository(ApiClient.getApiEndPoint())

    val email = ObservableField<String?>()
    val name = ObservableField<String?>()
    val landlordEmail = ObservableField<String?>()
    val password = ObservableField<String?>()
    val tenantIsSelect = ObservableField<Boolean>()


    fun registerRepositoryObserver() = registerRepository.registerResponse

    fun giveParamsGetList(user: User) {
        registerRepository.postRegisterInfo(user)
    }

    fun registerClicked(){
        var user = User()
        user.email = email.get()
        user.name = name.get()
        user.landlordEmail = landlordEmail.get()
        user.password = password.get()
        if(tenantIsSelect.get() == true){
            user.type = "tenant"
        }else{
            user.type = "landlord"
        }
        giveParamsGetList(user)



    }

}