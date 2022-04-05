package com.example.shopee.Class

import android.os.Parcelable
import com.example.shopee.Room.Stock
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


@Serializable
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

@Parcelize
data class StockArgs(

    val ID: Int,

    val type: String,

    val brand: String,

    val model: String,

    val sex: String,

    val price: String,

    val size: List<String>,

    val image_url: List<String>
) : Parcelable

