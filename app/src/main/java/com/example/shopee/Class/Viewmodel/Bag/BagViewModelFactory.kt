package com.example.shopee.Class.Viewmodel.Bag

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopee.Class.Viewmodel.Main.MainViewModel
import com.example.shopee.Room.StockDatabase

class BagViewModelFactory(val application: Application):  ViewModelProvider.Factory  {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BagViewModel::class.java)){
            BagViewModel(application) as T
        } else {
            throw IllegalArgumentException("ViewModel not found!")
        }
    }
}