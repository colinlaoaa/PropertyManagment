package com.liao.propertymanagement.view_model.auth

import androidx.lifecycle.MutableLiveData
import com.liao.propertymanagement.api.EndPoint
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.Login
import com.liao.propertymanagement.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(private val endPoint: EndPoint) {
    val loginResponse: MutableLiveData<Login?> by lazy {
        MutableLiveData<Login?>()
    }


    fun postLoginInfo(user: User) {
        endPoint.postLogin(user).enqueue(object : Callback<Login> {
            override fun onFailure(call: Call<Login>, t: Throwable) {
                loginResponse.postValue(null)
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                var list = response.body()
                loginResponse.postValue(list)
                if (list != null ) {
                    SessionManager.login(
                        list.token, list.user.email, list.user._id, list.user.type, list.user.name,
                        list.user.landlordEmail
                    )

                }
            }
        })
    }
}