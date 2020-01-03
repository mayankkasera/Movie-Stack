package com.example.qrcode.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.ui.moviedetail.cast.CastFragment
import com.example.moviestack.ui.moviedetail.info.InfoFragment
import com.example.moviestack.ui.moviedetail.review.ReviewFragment
import com.example.moviestack.ui.common.movielist.MovieListFragment

class MainViewPagerAdapter(fm : FragmentManager,var id :String) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
               InfoFragment.newInstance(id)
            }

            1 -> {
                CastFragment.newInstance(id)

            }

            2 -> {
                ReviewFragment.newInstance(id)

            }

            3 -> {
                MovieListFragment.newInstance(id)

            }


            else -> {
                InfoFragment.newInstance(id)

            }
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->  "Info"
            1 ->  "Cast"
            2 ->  "Reviews"
            3 ->  "Similer"
            else -> ""
        }
    }


}