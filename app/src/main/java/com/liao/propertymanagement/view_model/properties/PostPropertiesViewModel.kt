package com.liao.propertymanagement.view_model.properties

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.api.ApiClient
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.Properties

class PostPropertiesViewModel : ViewModel() {
    val address  = ObservableField<String?>()
    val city = ObservableField<String?>()
    val country  = "USA"
    val latitude = ObservableField<String?>()
    val longitude  = ObservableField<String?>()
    val mortageInfo = ObservableField<Boolean?>()
    val propertyStatus  = ObservableField<Boolean?>()
    val purchasePrice = ObservableField<String?>()
    val state = ObservableField<String?>()
    val userId = SessionManager.getUserId()
    val userType = SessionManager.getUserType()


    private val propertiesListRepository =
        PostPropertiesRepository(ApiClient.getApiEndPoint())

    fun postPropertiesListRepositoryObserver() = propertiesListRepository.listPropertiesGetRepository


    private fun giveParamsGetList(properties:Properties) {
        propertiesListRepository.postPropertiesData(properties)
    }

    fun setMortageInfoCheckboxTrue(){
        mortageInfo.set(true)
    }
    fun setMortageInfoCheckboxFalse(){
        mortageInfo.set(false)
    }

    fun setPropertyStatusCheckboxTrue(){
        propertyStatus.set(true)
    }
    fun setPropertyStatusCheckboxFalse(){
        propertyStatus.set(false)
    }

    fun confirmButtonClicked(){
        var properties = Properties()
        properties.address = address.get()
        properties.city = city.get()
        properties.country = country
        properties.latitude = latitude.get()
        properties.longitude = longitude.get()
        properties.mortageInfo = mortageInfo.get()
        properties.propertyStatus = propertyStatus.get()
        properties.purchasePrice = purchasePrice.get()
        properties.state = state.get()
        properties.userId = userId
        properties.userType = userType
        giveParamsGetList(properties)

    }
}