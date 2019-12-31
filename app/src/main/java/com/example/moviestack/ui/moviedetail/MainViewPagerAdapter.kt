package com.example.qrcode.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.ui.moviedetail.info.InfoFragment

class MainViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
               InfoFragment()
            }

            1 -> {
                InfoFragment()

            }

            2 -> {
                InfoFragment()

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