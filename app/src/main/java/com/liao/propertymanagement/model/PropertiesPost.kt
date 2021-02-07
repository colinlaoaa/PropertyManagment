package com.liao.propertymanagement.model

import android.graphics.Bitmap

data class PropertiesPost(
    val `data`: Properties,
    val error: Boolean,
    val message: String
)

data class Properties(
    var __v: Int?=null,
    var _id: String?=null,
    var address: String?=null,
    var city: String?=null,
    var country: String?=null,
    var latitude: String?=null,
    var longitude: String?=null,
    var mortageInfo: Boolean?=null,
    var propertyStatus: Boolean?=null,
    var purchasePrice: String?=null,
    var state: String?=null,
    var userId: String?=null,
    var userType: String?=null,
    var image: String?=null
)

data class PhotoInfo(
    var path:String,
    var photo:Bitmap
)