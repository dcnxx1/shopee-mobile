package com.example.shopee.Room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock")
data class Stock (
    @PrimaryKey
    val type: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "model")
    val model: String,
    @ColumnInfo(name = "sex")
    val sex: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "size")
    val size: List<String>,
    @ColumnInfo(name = "image_url")
    val image_url: List<String>
)