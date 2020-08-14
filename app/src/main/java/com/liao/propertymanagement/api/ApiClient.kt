package com.liao.propertymanagement.api


import com.liao.propertymanagement.app.Config
import com.liao.propertymanagement.app.EndPoints
import com.liao.propertymanagement.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET(EndPoints.PropertiesList)
    fun getPropertiesList(

    ): Call<PropertiesGet>

    @POST(EndPoints.PropertiesList)
    fun postPropertiesList(
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
}