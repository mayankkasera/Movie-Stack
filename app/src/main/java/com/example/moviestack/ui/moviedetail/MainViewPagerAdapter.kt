package com.example.qrcode.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.api.pojo.Review
import com.example.moviestack.ui.moviedetail.cast.CastFragment
import com.example.moviestack.ui.moviedetail.info.InfoFragment
import com.example.moviestack.ui.moviedetail.review.ReviewFragment

class MainViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
               InfoFragment()
            }

            1 -> {
                CastFragment()

            }

            2 -> {
                ReviewFragment()

            }

            3 -> {
                InfoFragment()

            }

            4 -> {
                InfoFragment()

            }

            5 -> {
                InfoFragment()

            }

            else -> {
                InfoFragment()

            }
        }
    }

    override fun getCount(): Int {
        return 6
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->  "Info"
            1 ->  "Cast"
            2 ->  "Reviews"
            3 ->  "Comments"
            4 ->  "Related"
            5 ->  "Similar"
            else -> ""
        }
    }


}