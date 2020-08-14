package com.liao.propertymanagement.view_model.properties

import androidx.lifecycle.MutableLiveData
import com.liao.propertymanagement.api.EndPoint
import com.liao.propertymanagement.model.Properties
import com.liao.propertymanagement.model.PropertiesPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostPropertiesRepository(private val endPoint: EndPoint) {
    val listPropertiesGetRepository: MutableLiveData<PropertiesPost> by lazy {
        MutableLiveData<PropertiesPost>()
    }


    fun postPropertiesData(properties: Properties) {
        endPoint.postPropertiesList(properties).enqueue(object : Callback<PropertiesPost> {
            override fun onFailure(call: Call<PropertiesPost>, t: Throwable) {
                listPropertiesGetRepository.postValue(null)
            }

            override fun onResponse(call: Call<PropertiesPost>, response: Response<PropertiesPost>) {
                var list = response.body()
                listPropertiesGetRepository.postValue(list)
            }
        })
    }
}