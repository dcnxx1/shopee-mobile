package com.example.shopee.Filter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.example.shopee.Class.FilterOption
import com.example.shopee.Class.Viewmodel.Main.MainViewModel
import com.example.shopee.R
import com.example.shopee.databinding.ActivityMainBinding
import com.example.shopee.databinding.FragmentArticleBinding


class FilterAdapter(binding: FragmentArticleBinding, val thisViewModel: MainViewModel) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    var filterClass = Filter(binding)

    private val diffUtil = object: DiffUtil.ItemCallback<FilterOption>(){
        override fun areItemsTheSame(oldItem: FilterOption, newItem: FilterOption): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FilterOption, newItem: FilterOption): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        var inflater = LayoutInflater.from(parent.context).inflate(R.layout.filter_option, parent, false)
        return FilterViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = differ.currentList[position]

        holder.filterButton.text = filter.displayType

        filterClass.handleFilterButton(holder, position, thisViewModel, filter, differ, this)

    }






    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class FilterViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val filterButton = view.findViewById<TextView>(R.id.filterOptionType)
        val filterClose = view.findViewById<ImageView>(R.id.filterClose)
    }








}