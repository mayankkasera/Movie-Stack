package com.example.moviestack.ui.persondetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.ui.common.credits.CreditFragment
import com.example.moviestack.ui.common.credits.CreditType
import com.example.moviestack.ui.common.movielist.MovieListFragment
import com.example.moviestack.ui.common.movielist.MovieListType
import com.example.moviestack.ui.moviedetail.info.InfoFragment
import com.example.moviestack.ui.moviedetail.review.ReviewFragment
import com.example.moviestack.ui.persondetail.info.PersonInfoFragment

class PersionViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                PersonInfoFragment()
            }

            1 -> {
                var movieListType = MovieListType(data = "2942",type = MovieListType.Type.SMILER);
                MovieListFragment.newInstance(movieListType)
            }

            2 -> {
                var movieListType = MovieListType(data = "2942",type = MovieListType.Type.SMILER);
                MovieListFragment.newInstance(movieListType)
            }

            else -> {
                PersonInfoFragment()
            }
        }
    }

    override fun getCount(): Int {
        return  3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->  "Info"
            1 ->  "Movies"
            2 ->  "Tv shows"
            else -> ""
        }
    }
}