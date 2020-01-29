package com.codeinger.moviestack.ui.persondetail.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.codeinger.moviestack.ui.common.movielist.simplelist.MovieListFragment
import com.codeinger.moviestack.ui.common.ListType
import com.codeinger.moviestack.ui.persondetail.info.PersonInfoFragment

class PersionViewPagerAdapter(fm : FragmentManager,var id :String) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                Log.i("sdjcbjsdc","PersionViewPagerAdapter 0 ")
                PersonInfoFragment.newInstance(id)
            }

            1 -> {
                Log.i("sdjcbjsdc","PersionViewPagerAdapter 1 ")
                var movieListType =
                    ListType(
                        data = id,
                        type = ListType.Type.MOVIE_CREDITS
                    );
                MovieListFragment.newInstance(movieListType)
            }

            2 -> {
                Log.i("sdjcbjsdc","PersionViewPagerAdapter 2 ")
                var movieListType =
                    ListType(
                        data = id,
                        type = ListType.Type.TV_CREDITS
                    );
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