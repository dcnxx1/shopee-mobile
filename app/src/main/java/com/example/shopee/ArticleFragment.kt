package com.example.shopee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopee.Class.StockAdapterHome
import com.example.shopee.Class.Viewmodel.Main.MainViewModel
import com.example.shopee.Filter.Filter
import com.example.shopee.Filter.FilterAdapter
import com.example.shopee.databinding.FragmentArticleBinding


class ArticleFragment : Fragment() {
    private lateinit var filterAdapter : FilterAdapter
    private lateinit var filter : Filter
    private lateinit var binding : FragmentArticleBinding
    val thisViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentArticleBinding.inflate(inflater)
        thisViewModel.initViewModel()
        filterAdapter = FilterAdapter(binding, thisViewModel)
        setFilterAdapter()
        filter = Filter(binding)

        setAdapter()

        return binding.root
    }



    override fun onStart() {
        super.onStart()
        filter.checkButtons(binding)
        filter.handleFilterChecks(filterAdapter, thisViewModel)
    }


    private fun setAdapter(){
        val manager = LinearLayoutManager(this.context)
        manager.orientation = GridLayoutManager.VERTICAL

        thisViewModel.modifiedStock.observe(viewLifecycleOwner){
            val adapterHome = StockAdapterHome(it)
            adapterHome.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            binding.recyclerHome.adapter = adapterHome
        }
    }


    override fun onResume() {
        super.onResume()
        
    }

    private fun setFilterAdapter(){
        val manager = LinearLayoutManager(this.context)
        manager.orientation = LinearLayoutManager.HORIZONTAL


        thisViewModel.filterOptions.observe(viewLifecycleOwner) {
            filterAdapter.differ.submitList(it)
        }


        binding.recyclerFilter.apply {
            adapter = filterAdapter

            layoutManager = manager
            setHasFixedSize(true)
        }
    }



}