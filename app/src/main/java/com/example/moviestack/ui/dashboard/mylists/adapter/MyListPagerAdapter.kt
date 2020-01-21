package com.example.moviestack.ui.dashboard.mylists.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.pojo.MyList
import com.example.moviestack.ui.dashboard.mylists.mylistdetail.MyListDetailFragment

class MyListPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
             0 -> MyListDetailFragment.newInstance(MyList.Type.MOVIE)
             1 ->  MyListDetailFragment.newInstance(MyList.Type.TV_SHOW)
            else -> MyListDetailFragment()
         }
    }

    override fun getCount(): Int {
         return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->  "Movie"
            1 ->  "Tv Show"
            else -> ""
        }
    }
}