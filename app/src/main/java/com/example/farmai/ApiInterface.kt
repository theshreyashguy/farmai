package com.example.farmai
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT

interface ApiInterface {
    @GET("data") // This will append "data" to the base URL
    fun getData(): Call<List<Data>>


}