package com.example.shopee.Filter


import android.util.Log


import android.widget.LinearLayout
import androidx.core.view.get
import androidx.recyclerview.widget.AsyncListDiffer


import com.devzone.checkabletextview.CheckableTextView
import com.example.shopee.Class.FilterOption
import com.example.shopee.Class.Stock
import com.example.shopee.Class.ViewModel.MainViewModel
import com.example.shopee.MainActivity

import com.example.shopee.databinding.ActivityMainBinding





class Filter(binding: ActivityMainBinding) {

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

    var buttonList = listOf(
        ObjectConnectors(1, type = "shoe", btn = binding.fOptionShoe, displayType = "Schoenen", 0),
        ObjectConnectors(2, type= "skirt", btn = binding.fOptionSkirt, displayType = "Jurken", 0),
        ObjectConnectors(3, type="jacket", btn= binding.fOptionJacket, displayType = "Jassen",0),
        ObjectConnectors(4, type="pants", btn = binding.fOptionPants, displayType = "Broeken", 0),
        ObjectConnectors(5, type="earring", btn = binding.fOptionEarring, displayType = "Oorbellen",0)
    )





    fun handleFilterButton(
        holder: FilterAdapter.FilterViewHolder,
        position: Int,
        thisViewModel: MainViewModel,
        filter: FilterOption,
        differ: AsyncListDiffer<FilterOption>,
        filterAdapter: FilterAdapter
    ) {
        holder.filterButton.setOnClickListener {
            buttonList.forEach { btn ->
            if(btn.displayType == holder.filterButton.text){
                btn.btn.setChecked(false)
                thisViewModel.filterOptions.value?.remove(filter)

                differ.submitList(thisViewModel.filterOptions.value)
                filterAdapter.notifyItemRemoved(position)
                filterAdapter.notifyItemChanged(position)
                updateModified(thisViewModel)
            }
        }

        }

        holder.filterClose.setOnClickListener {

        }
    }

    fun handleFilterChecks(filterAdapter: FilterAdapter, thisViewModel: MainViewModel){
        buttonList.forEach { btn ->
            btn.btn.setOnCheckChangeListener { view, isChecked ->
                var currentSelectedFilter = FilterOption(btn.id, btn.type, btn.displayType)
                if(filterAdapter.differ.currentList.contains(currentSelectedFilter)){
                    thisViewModel.filterOptions.value?.remove(FilterOption(btn.id, btn.type, btn.displayType))
                    filterAdapter.differ.submitList(thisViewModel.filterOptions.value)
                    filterAdapter.notifyDataSetChanged()
                    updateModified(thisViewModel)
                } else {
                    thisViewModel.filterOptions.value?.add(FilterOption(btn.id, btn.type, btn.displayType))
                    filterAdapter.differ.submitList(thisViewModel.filterOptions.value)
                    filterAdapter.notifyDataSetChanged()
                    updateModified(thisViewModel)
                }

            }
        }
    }

    fun updateModified(thisViewModel: MainViewModel){
        var tempArray = mutableListOf<Stock>()
        if(thisViewModel.filterOptions.value?.size != 0){
            thisViewModel.filterOptions.value?.forEach {fOption ->
                thisViewModel.originalStock.value?.filter { pr -> pr.type == fOption.type }?.let { tempArray.addAll(it) }
            }
            thisViewModel.modifiedStock.postValue(tempArray)
        } else {
            thisViewModel.modifiedStock.value = thisViewModel.originalStock.value as MutableList<Stock>
        }

    }

    data class ObjectConnectors(
        val id: Int,
        val type: String,
        val btn: CheckableTextView,
        val displayType: String,
        val position : Int,
    )
}
