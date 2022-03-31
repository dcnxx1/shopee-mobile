package com.example.shopee.Room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shopee.Class.Converters

@Database(entities = [Stock::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class StockDatabase() : RoomDatabase() {

    abstract fun stockDao() : StockDao



    companion object{
        @Volatile
        private var INSTANCE : StockDatabase? = null
        fun getDatabase(context: Context) : StockDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !== null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, StockDatabase::class.java, "stock_lite").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}