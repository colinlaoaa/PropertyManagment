package com.liao.propertymanagement.view_model.properties

import androidx.lifecycle.MutableLiveData
import com.liao.propertymanagement.api.EndPoint
import com.liao.propertymanagement.model.Properties
import com.liao.propertymanagement.model.PropertiesGet
import com.liao.propertymanagement.model.PropertiesPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPropertiesRepository(private val endPoint: EndPoint) {
    val propertiesResponse: MutableLiveData<List<Properties>> by lazy {
        MutableLiveData<List<Properties>>()
    }
    var mList = ArrayList<Properties>()


    fun getPropertiesInfo(userId: String?) {
        endPoint.getPropertiesList().enqueue(object : Callback<PropertiesGet> {
            override fun onFailure(call: Call<PropertiesGet>, t: Throwable) {
                propertiesResponse.postValue(null)
            }

            override fun onResponse(call: Call<PropertiesGet>, response: Response<PropertiesGet>) {
                var list = response.body()
                var mList1 = ArrayList<Properties>()
                if (list?.data != null) {
                    for (item in list.data) {
                        if (item.userId == userId) {
                            mList1.add(item)
                        }
                    }
                    mList = mList1
                    propertiesResponse.postValue(mList1)
                }
            }
        })
    }


    fun deletePropertiesInfo(id: String?) {
        endPoint.deletePropertiesList(id).enqueue(object : Callback<PropertiesPost> {
            override fun onFailure(call: Call<PropertiesPost>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<PropertiesPost>,
                response: Response<PropertiesPost>
            ) {
                var mList2 = mList
                var ii: Properties? = null
                if (response.body()?.error == false) {
                    for (i in mList2) {
                        if (i._id == id) {
                            ii = i
                        }
                    }
                    mList.remove(ii)
                }
                propertiesResponse.postValue(mList)
            }
        })
    }


    fun updatePropertiesInfo(id: String?,properties: Properties) {
        endPoint.updatePropertiesList(id,properties).enqueue(object : Callback<PropertiesPost> {
            override fun onFailure(call: Call<PropertiesPost>, t: Throwable) {
            }

            override fun onResponse(call: Call<PropertiesPost>, response: Response<PropertiesPost>) {

            }
        })
    }
}