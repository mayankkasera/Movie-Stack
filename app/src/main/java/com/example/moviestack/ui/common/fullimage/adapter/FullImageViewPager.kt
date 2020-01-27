package com.example.moviestack.ui.common.fullimage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.pojo.ImagesDeatail
import com.example.moviestack.ui.common.fullimage.FullImageFragment
import com.example.moviestack.ui.moviedetail.DetailData


class FullImageViewPager (fm : FragmentManager,var list : List<ImagesDeatail>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
           return FullImageFragment.newInstance(list.get(position)?.filePath!!)
    }

    override fun getCount(): Int {
       return list.size
    }
}