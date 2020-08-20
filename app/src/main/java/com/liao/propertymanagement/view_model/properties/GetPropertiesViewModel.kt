package com.liao.propertymanagement.view_model.properties

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.api.ApiClient
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.Properties

class GetPropertiesViewModel : ViewModel() {
    val address  = ObservableField<String?>()
    val city = ObservableField<String?>()
    val country  = "USA"
    val mortageInfo = ObservableField<Boolean?>()
    val propertyStatus  = ObservableField<Boolean?>()
    val purchasePrice = ObservableField<String?>()
    val state = ObservableField<String?>()
    val userId = SessionManager.getUserId()
    val userType = SessionManager.getUserType()


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

    fun onDeleteButtonClicked(id:String){
        getPropertiesRepository.deletePropertiesInfo(id)
    }

    fun onUpdateButtonClicked(id:String,image:String,latitude:String,longitude:String){
        var properties = Properties()
        properties.address = address.get()
        properties.city = city.get()
        properties.country = country
        //properties.latitude= "59"
        //properties.longitude="60"
        properties.mortageInfo = mortageInfo.get()
        properties.propertyStatus = propertyStatus.get()
        properties.purchasePrice = purchasePrice.get()
        properties.state = state.get()
        properties.userId = userId
        properties.userType = userType
        properties.image = image
        getPropertiesRepository.updatePropertiesInfo(id,properties)
        onRefreshButtonClicked()
        address.set("")
        city.set("")
        mortageInfo.set(false)
        propertyStatus.set(false)
        purchasePrice.set("")
        state.set("")
    }
}