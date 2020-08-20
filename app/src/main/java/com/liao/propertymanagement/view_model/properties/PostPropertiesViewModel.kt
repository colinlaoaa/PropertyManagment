package com.liao.propertymanagement.view_model.properties

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.api.ApiClient
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.PhotoInfo
import com.liao.propertymanagement.model.Properties

class PostPropertiesViewModel : ViewModel() {
    private val bitmapPhoto: MutableLiveData<PhotoInfo> by lazy {
        MutableLiveData<PhotoInfo>()
    }

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
    var itemImageLink:String? = null


    private val propertiesListRepository =
        PostPropertiesRepository(ApiClient.getApiEndPoint())


    fun postPropertiesListRepositoryObserver() = propertiesListRepository.listPropertiesGetRepository

    fun imageLinkObserver() = propertiesListRepository.imageLink

    fun bitmapPhotoObserver() = bitmapPhoto


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
        properties.image = itemImageLink
        giveParamsGetList(properties)

    }

    fun clearProperties(){
        address.set("")
        city.set("")
        latitude.set("")
        longitude.set("")
        purchasePrice.set("")
        state.set("")
    }

    fun transferBitmap(bitmap: Bitmap,path:String) {
        var photoInfo = PhotoInfo(path,bitmap)
        bitmapPhoto.postValue(photoInfo)
    }

    fun uploadImageConfirm(path:String){
        propertiesListRepository.uploadImage(path)
    }

    fun getImageLink(imageLink:String){
        itemImageLink=imageLink
    }
}