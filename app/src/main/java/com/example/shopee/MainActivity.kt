package com.example.shopee


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.shopee.Class.StockAdapterHome
import com.example.shopee.Class.Viewmodel.Main.MainViewModel
import com.example.shopee.Filter.Filter
import com.example.shopee.Filter.FilterAdapter
import com.example.shopee.databinding.ActivityMainBinding
import com.skydoves.androidbottombar.BottomMenuItem


class MainActivity : AppCompatActivity() {
    private lateinit var thisViewModel : MainViewModel
    private lateinit var viewModelFactory : ViewModelProvider.Factory
    private lateinit var binding : ActivityMainBinding
    private lateinit var filterAdapter : FilterAdapter
    private lateinit var filter : Filter

    // initialize viewModel
    private fun initViewModel(){
        viewModelFactory = ViewModelProvider.NewInstanceFactory()
        thisViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        ViewModelProvider(this, viewModelFactory)[thisViewModel::class.java]
    }

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bottomBarAdd()
       initViewModel()
       thisViewModel.initViewModel()
       setAdapter()
       filterAdapter = FilterAdapter(binding, thisViewModel)
       setFilterAdapter()
       filter = Filter(binding)

   }

    override fun onStart() {
        super.onStart()
    
        filter.checkButtons(binding)
        filter.handleFilterChecks(filterAdapter, thisViewModel)

    }

    private fun setAdapter(){
            val manager = LinearLayoutManager(this)
            manager.orientation = LinearLayoutManager.VERTICAL

            thisViewModel.modifiedStock.observe(this){
            val adapterHome = StockAdapterHome(it, this)

            binding.recyclerHome.adapter = adapterHome
            }
    }





  private fun setFilterAdapter(){
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL


        thisViewModel.filterOptions.observe(this) {
            filterAdapter.differ.submitList(it)
        }

        binding.recyclerFilter.apply {
                adapter = filterAdapter
                layoutManager = manager
                setHasFixedSize(true)
            }
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