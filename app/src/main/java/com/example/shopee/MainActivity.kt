package com.example.shopee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopee.Class.FilterOption
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

    // init viewModel
    fun initViewModel(){
        viewModelFactory = ViewModelProvider.NewInstanceFactory()
        thisViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        ViewModelProvider(this, viewModelFactory)[thisViewModel::class.java]
    }

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
       initViewModel()
       thisViewModel.getData()
       setAdapter()
       thisViewModel.loadFilter()
       Filter(binding).checkButtons(binding)
   }

    override fun onStart() {
        super.onStart()
        goToBag()
        var manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        thisViewModel.filterOptions.observe(this) {
            binding.recyclerFilter.layoutManager = manager

            filterAdapter = FilterAdapter(it, binding)
            binding.recyclerFilter.adapter = filterAdapter
            binding.recyclerFilter.setHasFixedSize(false)
        }
        addItem()

    }

    fun setAdapter(){
            var manager = LinearLayoutManager(this)
            manager.orientation = LinearLayoutManager.VERTICAL

            thisViewModel.originalStock.observe(this){
            var adapterHome = StockAdapterHome(it)
            binding.recyclerHome.adapter = adapterHome
            }

    }

    fun goToBag(){
        val intent = Intent(this, Bag::class.java)
        binding.bag.setOnClickListener {
            startActivity(intent)
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
    }


    fun addItem(){
        binding.fOptionPants.setOnCheckChangeListener { view, isChecked ->
            thisViewModel.filterOptions.value?.add(FilterOption(6, "cap", "Capper"))
        }
    }
}