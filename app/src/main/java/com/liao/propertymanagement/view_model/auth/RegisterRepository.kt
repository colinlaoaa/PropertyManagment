package com.liao.propertymanagement.view_model.auth

import androidx.lifecycle.MutableLiveData
import com.liao.propertymanagement.api.EndPoint
import com.liao.propertymanagement.model.Register
import com.liao.propertymanagement.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepository(private val endPoint: EndPoint) {
    val registerResponse: MutableLiveData<Register?> by lazy {
        MutableLiveData<Register?>()
    }


    fun postRegisterInfo(user: User) {
        endPoint.postRegister(user).enqueue(object : Callback<Register> {
            override fun onFailure(call: Call<Register>, t: Throwable) {
                registerResponse.postValue(null)
            }

            override fun onResponse(call: Call<Register>, response: Response<Register>) {
                var list = response.body()
                registerResponse.postValue(list)
            }
        })
    }
}