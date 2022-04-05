package com.example.shopee


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.shopee.Class.StockAdapterHome
import com.example.shopee.Class.Viewmodel.Main.MainViewModel
import com.example.shopee.Filter.Filter
import com.example.shopee.Filter.FilterAdapter
import com.example.shopee.databinding.ActivityMainBinding
import com.skydoves.androidbottombar.BottomMenuItem


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
       bottomBarAdd()


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
       return navController.navigateUp() || super.onSupportNavigateUp()
    }


    fun bottomBarAdd(){
        binding.bottomBar.addBottomMenuItems(mutableListOf(
            BottomMenuItem(this)
                .setTitle("Artikelen")
                .setTitleColorRes(R.color.beige)
                .setTitleActiveColor(resources.getColor(R.color.beige_white))
                .setIcon(R.drawable.ic_baseline_home_24)
                .setIconSize(20)
                .build(),
            BottomMenuItem(this)
                .setTitle("Winkeltas")
                .setTitleColorRes(R.color.beige)
                .setTitleActiveColor(resources.getColor(R.color.beige_white))
                .setIcon(R.drawable.ic_baseline_shopping_bag_24)
                .setIconSize(20)
                .build()
        ))
    }

}