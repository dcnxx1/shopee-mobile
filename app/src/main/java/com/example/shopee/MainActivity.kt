package com.example.shopee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopee.Class.FilterOption
import com.example.shopee.Class.Stock
import com.example.shopee.Class.StockAdapterHome
import com.example.shopee.Class.ViewModel.MainViewModel
import com.example.shopee.Filter.Filter
import com.example.shopee.Filter.FilterAdapter
import com.example.shopee.databinding.ActivityMainBinding

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
       initViewModel()
       thisViewModel.initViewModel()
       setAdapter()
       filterAdapter = FilterAdapter(binding, thisViewModel)
       setFilterAdapter()
       filter = Filter(binding)
   }

    override fun onStart() {
        super.onStart()
        goToBag()
        filter.checkButtons(binding)
        filter.handleFilterChecks(filterAdapter, thisViewModel)

    }

    private fun setAdapter(){
            val manager = LinearLayoutManager(this)
            manager.orientation = LinearLayoutManager.VERTICAL

            thisViewModel.modifiedStock.observe(this){
            val adapterHome = StockAdapterHome(it)
            binding.recyclerHome.adapter = adapterHome
            }
    }

    private fun goToBag(){
        val intent = Intent(this, Bag::class.java)
        binding.bag.setOnClickListener {
            startActivity(intent)
            overridePendingTransition(R.anim.enter, R.anim.exit)
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



}