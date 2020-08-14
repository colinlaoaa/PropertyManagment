package com.liao.propertymanagement.view_model.properties

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.api.ApiClient
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.Properties

class GetPropertiesViewModel : ViewModel() {
    private val getPropertiesRepository = GetPropertiesRepository(ApiClient.getApiEndPoint())

    fun getPropertiesObservation(): MutableLiveData<List<Properties>> {
        return getPropertiesRepository.propertiesResponse
    }

    fun makeCallGetPropertiesInfo(){
        getPropertiesRepository.getPropertiesInfo(SessionManager.getUserId())
    }

    fun onRefreshButtonClicked() {
        getPropertiesRepository.getPropertiesInfo(SessionManager.getUserId())
    }
}