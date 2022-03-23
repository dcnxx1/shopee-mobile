package com.example.shopee.Data



import android.content.res.Resources
import android.util.Log
import com.example.shopee.Class.Stock
import com.example.shopee.R
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object OnlineData {
    private val URL = "https://shopee-backend.herokuapp.com"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun makeCall() : ApiCall {
           return retrofit.create(ApiCall::class.java)
    }


}

