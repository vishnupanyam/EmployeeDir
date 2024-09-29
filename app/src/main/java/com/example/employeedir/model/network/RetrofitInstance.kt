package com.example.employeedir.model.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val gson : Gson = GsonBuilder().setLenient().create()
    val apiInterface : ApiInterface by lazy {
        Retrofit.Builder().baseUrl("https://s3.amazonaws.com/sq-mobile-interview/").addConverterFactory(
            GsonConverterFactory.create(gson)).build().create(ApiInterface::class.java)
    }
}