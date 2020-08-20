package com.liao.propertymanagement.view_model.user_profile

import android.util.Log
import com.liao.propertymanagement.api.ApiClient
import com.liao.propertymanagement.api.EndPoint
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.UploadImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class UserProfileRepostory(private val apiService: EndPoint){


    // using retrofit and api
    fun uploadImage(path: String) {
        var file = File(path)



        var requestFile = RequestBody.create(MediaType.parse("image/*"), file)
        var body = MultipartBody.Part.createFormData("image", file.name, requestFile)
        var call = apiService.uploadPrescription(body)
        call.enqueue(object : retrofit2.Callback<UploadImage> {
            override fun onFailure(call: Call<UploadImage>?, t: Throwable?) {
            }

            override fun onResponse(
                call: Call<UploadImage>?,
                response: Response<UploadImage>?
            ) {
                Log.d("imageLink", response?.body().toString())
                var response = response?.body()?.data

                if (response != null) {
                    Log.d("imageLink", response.location)
                    println("999999999999999999999999999999999999999")
                    println(response.location)
                    SessionManager.saveProfileImage(response.location)
                }
            }

        })
    }
}