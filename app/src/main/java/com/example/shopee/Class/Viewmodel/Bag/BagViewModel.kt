package com.example.shopee.Class.Viewmodel.Bag

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopee.Room.Stock
import com.example.shopee.Room.StockDatabase
import kotlinx.coroutines.launch


class BagViewModel(application: Application) : AndroidViewModel(application) {
private val repository : StockRepository

    init{
        val stockDao = StockDatabase.getDatabase(application).stockDao()
        repository = StockRepository(stockDao)
    }

    fun getter(){
        viewModelScope.launch {
            repository.insertArticle(Stock("Hello", 2, "hoho", "lala", "heyy", "25.61", listOf("24", "255", "61"), listOf("for", "every", "img", "url", ":))")))
        }
    }



}