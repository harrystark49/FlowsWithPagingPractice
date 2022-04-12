package com.example.flowswithpagingpractice

import retrofit2.http.GET
import retrofit2.http.Query

interface Retro_Service {
    @GET("character")
    suspend fun getdata(@Query("page")page:Int):server_result
}