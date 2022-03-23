package com.example.shopee.Class

import com.google.gson.annotations.SerializedName

data class Stock(
    @SerializedName("id")
    val ID: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("size")
    val size: List<String>,
    @SerializedName("image_url")
    val image_url: List<String>
)

data class FilterOption(
    val id: Int,
    val type: String,
    val displayType: String

)

