package com.liao.propertymanagement.view_model.properties

import androidx.lifecycle.MutableLiveData
import com.liao.propertymanagement.api.EndPoint
import com.liao.propertymanagement.model.Properties
import com.liao.propertymanagement.model.PropertiesGet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPropertiesRepository(private val endPoint: EndPoint) {
    val propertiesResponse: MutableLiveData<List<Properties>> by lazy {
        MutableLiveData<List<Properties>>()
    }


    fun getPropertiesInfo(userId: String?) {
        endPoint.getPropertiesList().enqueue(object : Callback<PropertiesGet> {
            override fun onFailure(call: Call<PropertiesGet>, t: Throwable) {
                propertiesResponse.postValue(null)
            }

            override fun onResponse(call: Call<PropertiesGet>, response: Response<PropertiesGet>) {
                var list = response.body()
                var mList = ArrayList<Properties>()
                if (list?.data != null) {
                    for (item in list.data) {
                        if (item.userId == userId) {
                            mList.add(item)
                        }
                    }
                    propertiesResponse.postValue(mList)
                }
            }
        })
    }
}