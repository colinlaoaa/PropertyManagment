package com.liao.propertymanagement.api


import com.liao.propertymanagement.app.Config
import com.liao.propertymanagement.app.EndPoints
import com.liao.propertymanagement.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiClient {
    private val _endpoint: EndPoint by lazy {
        val client = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        client.create(EndPoint::class.java)
    }

    fun getApiEndPoint(): EndPoint {
        return _endpoint
    }

}


interface EndPoint {

    @GET(EndPoints.GetPropertiesList)
    fun getPropertiesList(
        //@Path("id") userId:String?
    ): Call<PropertiesGet>

    @POST(EndPoints.PropertiesList)
    fun postPropertiesList(
        @Body properties: Properties
    ): Call<PropertiesPost>


    @DELETE(EndPoints.DeletePropertiesList)
    fun deletePropertiesList(
        @Path("id") id:String?
    ): Call<PropertiesPost>

    @PUT(EndPoints.DeletePropertiesList)
    fun updatePropertiesList(
        @Path("id") id:String?,
        @Body properties: Properties
    ): Call<PropertiesPost>

    @GET(EndPoints.GetTenantsList)
    fun getTenantsList(

    ): Call<PropertiesGet>

    @POST(EndPoints.TenantsList)
    fun postTenantsList(
        @Body properties: Properties
    ): Call<PropertiesPost>

    @POST(EndPoints.Login)
    fun postLogin(
        @Body user: User
    ): Call<Login>

    @POST(EndPoints.Register)
    fun postRegister(
        @Body user: User
    ): Call<Register>


    @Multipart
    @POST(EndPoints.Photo)
    fun uploadPrescription(
        @Part body: MultipartBody.Part
    ): Call<UploadImage>
}