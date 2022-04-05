package com.example.shopee.Class

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shopee.ArticleFragmentDirections
import com.example.shopee.Class.Viewpager.ViewPagerAdapter
import com.example.shopee.MainActivity
import com.example.shopee.R

class StockAdapterHome(private val dataSet : List<Stock>) : RecyclerView.Adapter<StockAdapterHome.ViewHolderHome>(){

    class ViewHolderHome (binder: View) : RecyclerView.ViewHolder(binder){
        var brand: TextView
        var model : TextView
        var price : TextView
        var pager : ViewPager2
        var context = itemView.context
        init{
            pager = binder.findViewById(R.id.viewpager2)
            brand = binder.findViewById(R.id.brand)
            model = binder.findViewById(R.id.model)
            price = binder.findViewById(R.id.price)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockAdapterHome.ViewHolderHome {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.cardlayout, parent, false)
        return ViewHolderHome(view)
    }


    override fun onBindViewHolder(holder: ViewHolderHome, position: Int) {
    val viewPager = ViewPagerAdapter(dataSet[position].image_url)
        holder.pager.adapter = viewPager
        holder.brand.text = dataSet[position].brand
        holder.model.text = dataSet[position].model
        holder.price.text = "€ " + dataSet[position].price

        holder.itemView.setOnClickListener {
            val dataCaught = Stock(dataSet[position].ID, dataSet[position].type, dataSet[position].brand, dataSet[position].model, dataSet[position].sex, dataSet[position].price, dataSet[position].size, dataSet[position].image_url)
            val stockArgs = StockArgs(dataCaught.ID, dataCaught.type, dataCaught.brand, dataCaught.model, dataCaught.sex, dataCaught.price, dataCaught.size, dataCaught.image_url)
            val actionArgs = ArticleFragmentDirections.actionArticleFragmentToItemFragment(stockArgs)
            holder.itemView.findNavController().navigate(actionArgs)

        }

    }



    override fun getItemCount(): Int = dataSet.size
}