package com.example.shopee.Filter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devzone.checkabletextview.CheckableTextView
import com.example.shopee.Class.FilterOption
import com.example.shopee.MainActivity
import com.example.shopee.R
import com.example.shopee.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import java.util.function.UnaryOperator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class Filter(private val binding: ActivityMainBinding) {

    var showMenu = false
    fun checkButtons(binder: ActivityMainBinding){
            binder.filterButton.setOnClickListener {
                if(showMenu){
                    binder.layoutOrange.visibility = LinearLayout.VISIBLE
                    showMenu = false
                } else {
                    binder.layoutOrange.visibility = LinearLayout.GONE
                    showMenu = true
                }
            }
    }

    var buttonList = listOf<ObjectConnectors>(
        ObjectConnectors(1, type = "shoe", btn = binding.fOptionShoe, displayType = "Schoenen"),
        ObjectConnectors(2, type= "skirt", btn = binding.fOptionSkirt, displayType = "Jurken"),
        ObjectConnectors(3, type="jacket", btn= binding.fOptionJacket, displayType = "Jassen"),
        ObjectConnectors(4, type="pants", btn = binding.fOptionPants, displayType = "Broeken"),
        ObjectConnectors(5, type="earring", btn = binding.fOptionEarring, displayType = "Oorbellen")
    )






    data class ObjectConnectors(
        val id: Int,
        val type: String,
        val btn: CheckableTextView,
        val displayType: String
    )
}
