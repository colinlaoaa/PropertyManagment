package com.liao.propertymanagement.view_model.properties

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.liao.propertymanagement.api.EndPoint
import com.liao.propertymanagement.model.Properties
import com.liao.propertymanagement.model.PropertiesPost
import com.liao.propertymanagement.model.UploadImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PostPropertiesRepository(private val endPoint: EndPoint) {
    val listPropertiesGetRepository: MutableLiveData<PropertiesPost> by lazy {
        MutableLiveData<PropertiesPost>()
    }


    fun postPropertiesData(properties: Properties) {
        endPoint.postPropertiesList(properties).enqueue(object : Callback<PropertiesPost> {
            override fun onFailure(call: Call<PropertiesPost>, t: Throwable) {
                listPropertiesGetRepository.postValue(null)
            }

            override fun onResponse(
                call: Call<PropertiesPost>,
                response: Response<PropertiesPost>
            ) {

                var list = response.body()
                if (list != null) {
                    Log.d("imageLink", list.data.country!!)
                    Log.d("imageLink","here")
                }
                listPropertiesGetRepository.postValue(list)
            }
        })
    }

    val imageLink: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    // using retrofit and api
    fun uploadImage(path: String) {
        var file = File(path)



        var requestFile = RequestBody.create(MediaType.parse("image/*"), file)
        var body = MultipartBody.Part.createFormData("image", file.name, requestFile)
        var call = endPoint.uploadPrescription(body)
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
                    println(response.location)
                    imageLink.postValue(response.location)
                }
            }

        })
    }

}


