package com.example.shopee.Class.Viewmodel.Main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopee.Class.FilterOption
import com.example.shopee.Class.Stock
import com.example.shopee.Data.OnlineData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : ViewModel() {
    private val apiCaller = OnlineData
    val originalStock : MutableLiveData<List<Stock>> = MutableLiveData<List<Stock>>()
    val modifiedStock : MutableLiveData<MutableList<Stock>> = MutableLiveData<MutableList<Stock>>()
    val filterOptions : MutableLiveData<MutableList<FilterOption>> = MutableLiveData<MutableList<FilterOption>>()

    init{
        filterOptions.value = mutableListOf()
    }

    fun getData(){
    viewModelScope.launch {
        apiCaller.makeCall().getAllStock().enqueue(object : Callback<List<Stock>>{
            override fun onResponse(call: Call<List<Stock>>, response: Response<List<Stock>>) {
                val listData = response.body()
                listData.let { originalStock.postValue(it)}
                listData.let { modifiedStock.postValue(it as MutableList<Stock>?) }
            }

            override fun onFailure(call: Call<List<Stock>>, t: Throwable) {
                t.let { Log.v("lister", it.localizedMessage) }
            }
        })
    }

    }

    fun initViewModel(){
        getData()
    }



}