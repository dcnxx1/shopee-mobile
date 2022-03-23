package com.example.shopee.Class.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopee.Class.FilterOption
import com.example.shopee.Class.Stock
import com.example.shopee.Data.OnlineData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class MainViewModel() : ViewModel() {
    private val apiCaller = OnlineData


    // original stock -> this should not change
    val originalStock : MutableLiveData<List<Stock>> by lazy{
        MutableLiveData<List<Stock>>()
    }

    val modifiedStock : MutableLiveData<MutableList<Stock>> by lazy{
        MutableLiveData<MutableList<Stock>>()
    }

    val filterOptions : MutableLiveData<MutableList<FilterOption>> by lazy {
        MutableLiveData<MutableList<FilterOption>>()
    }

    val filterOptionHelper : MutableLiveData<MutableList<FilterOption>> by lazy{
        MutableLiveData<MutableList<FilterOption>>()
    }

    fun loadFilter(){
        val testArr = mutableListOf(
            FilterOption(1, type="shoe", displayType = "Schoenen"),
            FilterOption(2, type = "skirt", displayType = "Jurken"),
            FilterOption(3, type = "pants", displayType = "Broeken"),
            FilterOption(4, type="earring", displayType = "Oorbellen"),
            FilterOption(5, type="jacket", displayType = "Jassen")
            )
        filterOptions.postValue(testArr)
    }




    fun getData(){
    viewModelScope.launch {
        apiCaller.makeCall().getAllStock().enqueue(object : Callback<List<Stock>>{
            override fun onResponse(call: Call<List<Stock>>, response: Response<List<Stock>>) {
                val listData = response.body()
                listData.let { originalStock.postValue(it)}
            }

            override fun onFailure(call: Call<List<Stock>>, t: Throwable) {
                t.let { Log.v("lister", it.localizedMessage) }
            }
        })
    }

    }




}