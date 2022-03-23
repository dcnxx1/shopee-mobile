package com.example.shopee.Filter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.shopee.Class.FilterOption
import com.example.shopee.MainActivity
import com.example.shopee.R
import com.example.shopee.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class FilterAdapter( private var filterOptionArray : MutableList<FilterOption>, val filterViews: ActivityMainBinding) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    var filter = Filter(filterViews)

    class FilterViewHolder(binder: View) : RecyclerView.ViewHolder(binder){
        var filterOptionType : TextView
        var closeButton : ImageView
        init{
            filterOptionType = binder.findViewById(R.id.filterOptionType)
            closeButton = binder.findViewById(R.id.filterClose)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.filter_option, parent ,false)
        inflater.animation = AnimationUtils.loadAnimation(parent.context, R.anim.filter_fade)
        return FilterViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.filterOptionType.text = filterOptionArray[position].displayType
        respondClick(holder, position)
        addItem(position)
    }

    fun respondClick(holder: FilterViewHolder, position: Int){
        holder.filterOptionType.setOnClickListener{
            updateItem(position)
        }

        holder.closeButton.setOnClickListener {
            updateItem(position)
        }
    }

    fun addItem(position: Int){
        filter.buttonList.forEach { bList ->

            bList.btn.setOnCheckChangeListener { view, isChecked ->

                if(!filterOptionArray.contains(FilterOption(bList.id, bList.type, bList.displayType))){
                    filterOptionArray.add(FilterOption(bList.id, bList.type, bList.displayType))
                    notifyItemChanged(position)
                } else {
                    updateItem(position)
                    bList.btn.setChecked(false)
                }


            }
        }
        Log.i("tagger", filter.buttonList.size.toString())
    }


    private fun updateItem(position: Int){
        filterOptionArray.removeAt(position)

        notifyItemRemoved(position)
        notifyItemChanged(position)
    }



    override fun getItemCount(): Int {
        return filterOptionArray.size
    }
}