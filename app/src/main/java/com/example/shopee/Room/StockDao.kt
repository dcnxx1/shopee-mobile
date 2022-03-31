package com.example.shopee.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(stock : Stock)

    @Query("SELECT * FROM stock")
    fun getAll() : LiveData<List<Stock>>
}