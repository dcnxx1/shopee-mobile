package com.example.shopee

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.shopee.Class.Viewpager.ViewPagerAdapter
import com.example.shopee.databinding.FragmentItemBinding
import com.google.android.material.snackbar.Snackbar


class ItemFragment : Fragment() {
    private lateinit var binding : FragmentItemBinding
    var viewPagerAdapter : ViewPagerAdapter? = null

    private val args : ItemFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemBinding.inflate(layoutInflater)
        viewPagerAdapter = ViewPagerAdapter(args.stockArgs.image_url)
        setViewPagerSettings()
        return binding.root
    }

    fun setViewPagerSettings(){
        binding.itemViewPager.adapter = viewPagerAdapter
    }


}