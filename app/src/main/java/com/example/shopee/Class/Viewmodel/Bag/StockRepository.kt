package com.example.shopee.Class.Viewmodel.Bag

import androidx.lifecycle.LiveData
import com.example.shopee.Room.Stock
import com.example.shopee.Room.StockDao

class StockRepository(private val stockDao: StockDao) {

    suspend fun insertArticle( stock: Stock){
        stockDao.insertArticle(stock)
    }

    suspend fun getStock(): LiveData<List<Stock>> {
        return stockDao.getAll()
    }

}