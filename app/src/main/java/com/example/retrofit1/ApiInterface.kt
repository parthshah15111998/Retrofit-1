package com.example.retrofit1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    @GET("todos")
    fun getData():Call<List<MyData.MyDataItem>>

    @POST("todos")
    fun createUser(@Body user: MyData.MyDataItem?): Call<MyData.MyDataItem?>?
}