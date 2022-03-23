package com.example.shopee.Class.Viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.shopee.R


class ViewPagerAdapter(private val image_array : List<String>) :  RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

   inner class ImageViewHolder(var binding : View) : RecyclerView.ViewHolder(binding) {
      var imageSource : ImageView

      init{
          imageSource = binding.findViewById(R.id.image_id)
      }
        fun setData(imgUrl : String){
            Glide.with(binding)
                .load(imgUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageSource)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.pager_image, parent, false )
        return ImageViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setData(image_array[position])

    }

    override fun getItemCount() : Int = image_array.size
}