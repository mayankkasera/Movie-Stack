package com.codeinger.moviestack.ui.common.fullimage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.codeinger.moviestack.pojo.ImagesDeatail
import com.codeinger.moviestack.ui.common.fullimage.FullImageFragment


class FullImageViewPager (fm : FragmentManager,var list : List<ImagesDeatail>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
           return FullImageFragment.newInstance(list.get(position)?.filePath!!)
    }

    override fun getCount(): Int {
       return list.size
    }
}