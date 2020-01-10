package com.example.qrcode.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.ui.common.credits.CreditFragment
import com.example.moviestack.ui.common.credits.CreditType
import com.example.moviestack.ui.moviedetail.info.InfoFragment
import com.example.moviestack.ui.moviedetail.review.ReviewFragment
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingFragment
import com.example.moviestack.ui.common.movielist.MovieListType

class MainViewPagerAdapter(fm : FragmentManager,var id :String) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                InfoFragment.newInstance(id)
            }

            1 -> {
                var creditType = CreditType(data = id,type = CreditType.Type.CAST);
                CreditFragment.newInstance(creditType)
            }

            2 -> {
                ReviewFragment.newInstance(id)
            }

            3 -> {
                var creditType = MovieListType(data = "${id}",type = MovieListType.Type.SMILER );
                MovieListPaggingFragment.newInstance(creditType)
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