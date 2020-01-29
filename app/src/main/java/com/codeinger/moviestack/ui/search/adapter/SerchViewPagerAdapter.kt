package com.codeinger.moviestack.ui.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.codeinger.moviestack.ui.common.person.paging.PersonPagingFragment
import com.codeinger.moviestack.ui.common.ListType
import com.codeinger.moviestack.ui.common.movielist.paginglist.MovieListPaggingFragment
import com.codeinger.moviestack.ui.moviedetail.DetailData
import com.codeinger.moviestack.ui.persondetail.info.PersonInfoFragment

class SerchViewPagerAdapter (fm : FragmentManager, var data :String) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                var creditType = ListType(
                    data = data,
                    type = ListType.Type.MOVIE_SEARCH
                );
                MovieListPaggingFragment.newInstance(creditType,DetailData.Type.MOVIE)
            }

            1 -> {
                var creditType = ListType(
                    data = data,
                    type = ListType.Type.TV_SEARCH
                );
                MovieListPaggingFragment.newInstance(creditType,DetailData.Type.TV_SHOW)
            }

            2 -> {
                var movieListType =
                    ListType(
                        data = data,
                        type = ListType.Type.PERSON_SEARCH
                    );
                PersonPagingFragment.newInstance(movieListType)
            }

            else -> {
                PersonInfoFragment()
            }
        }
    }

    override fun getCount(): Int {
          return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->  "Movies"
            1 ->  "Tv shows"
            2 ->  "People"
            else -> ""
        }
    }

}