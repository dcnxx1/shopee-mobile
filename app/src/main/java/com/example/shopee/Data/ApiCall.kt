package com.example.shopee.Data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopee.Class.Stock
import com.example.shopee.R
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {
    @GET("/")
      fun getAllStock() : Call<List<Stock>>

    @GET("/heren")
      fun getMale() : Call<List<Stock>>

    @GET("/dames")
      fun getFemale() : Call<List<Stock>>

}