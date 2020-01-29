package com.codeinger.qrcode.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.codeinger.moviestack.ui.common.person.simple.PersonSimpleFragment
import com.codeinger.moviestack.ui.moviedetail.info.InfoFragment
import com.codeinger.moviestack.ui.moviedetail.review.ReviewFragment
import com.codeinger.moviestack.ui.common.movielist.paginglist.MovieListPaggingFragment
import com.codeinger.moviestack.ui.common.ListType
import com.codeinger.moviestack.ui.moviedetail.DetailData

class MainViewPagerAdapter (fm : FragmentManager,var id :String,var type : DetailData.Type) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                InfoFragment.newInstance(id,type)
            }

            1 -> {
                var creditType = ListType(data = id,type = ListType.Type.CAST);
                PersonSimpleFragment.newInstance(creditType,type)
            }

            2 -> {
                ReviewFragment.newInstance(id,type)
            }

            3 -> {
                var creditType = ListType(
                    data = "${id}",
                    type = ListType.Type.SMILER
                );
                MovieListPaggingFragment.newInstance(creditType,type)
            }

            else -> {
                InfoFragment.newInstance(id,type)
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