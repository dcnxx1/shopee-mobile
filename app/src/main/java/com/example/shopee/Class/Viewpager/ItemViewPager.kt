package com.example.shopee.Class.Viewpager

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopee.R

class ItemViewPager(val image_list : List<String>) : RecyclerView.Adapter<ItemViewPager.ItemAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = image_list.size

    inner class ItemAdapterViewHolder(private val viewHolder: View) : RecyclerView.ViewHolder(viewHolder){
       var imageView : ImageView

        init {
        imageView = viewHolder.findViewById(R.id.image_id)

        }


    }

}