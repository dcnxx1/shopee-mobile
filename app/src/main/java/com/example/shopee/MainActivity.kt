package com.example.shopee


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.shopee.Class.StockAdapterHome
import com.example.shopee.Class.Viewmodel.Main.MainViewModel
import com.example.shopee.Filter.Filter
import com.example.shopee.Filter.FilterAdapter
import com.example.shopee.databinding.ActivityMainBinding
import com.skydoves.androidbottombar.BottomMenuItem
import com.skydoves.androidbottombar.OnMenuItemSelectedListener


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
       bottomBarAdd()

    }


    override fun onBackPressed() {
        var currentDestination = findNavController(R.id.fragmentContainerView).currentDestination?.label

        when(currentDestination){
            "ArticleFragment" -> {
                binding.bottomBar.getBottomMenuItemView(0).apply {
                    unselectedBottomBarItem()
                    setIsActive(false)
                }

                binding.bottomBar.getBottomMenuItemView(1).apply {
                    selectedBottomBarItem()

                    setIsActive(true)
                }
            }
            "fragment_bag" -> {
                binding.bottomBar.getBottomMenuItemView(1).apply {
                    unselectedBottomBarItem()
                    setIsActive(false)
                }
                binding.bottomBar.getBottomMenuItemView(0).apply {
                    selectedBottomBarItem()
                    setIsActive(true)
                }
            }
        }
        super.onBackPressed()
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

        binding.bottomBar.onMenuItemSelectedListener =
            OnMenuItemSelectedListener { index, bottomMenuItem, fromUser ->
                if(fromUser){
                when(index){
                        0 -> {
                            findNavController(R.id.fragmentContainerView).popBackStack()
                            findNavController(R.id.fragmentContainerView).navigate(R.id.articleFragment)
                        }
                        1 -> {
                            findNavController(R.id.fragmentContainerView).navigate(R.id.bagFragment)
                        }
                    }
                }
            }
    }
}