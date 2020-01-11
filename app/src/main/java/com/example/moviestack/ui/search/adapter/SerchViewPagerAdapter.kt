package com.example.moviestack.ui.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.ui.common.credits.paging.PersonPagingFragment
import com.example.moviestack.ui.common.movielist.MovieListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingFragment
import com.example.moviestack.ui.common.movielist.simplelist.MovieListFragment
import com.example.moviestack.ui.persondetail.info.PersonInfoFragment

class SerchViewPagerAdapter (fm : FragmentManager, var data :String) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                var creditType = MovieListType(data = "raajneeti",type = MovieListType.Type.MOVIE_SEARCH );
                MovieListPaggingFragment.newInstance(creditType)
            }

            1 -> {
                var creditType = MovieListType(data = "Game of Thrones",type = MovieListType.Type.TV_SEARCH );
                MovieListPaggingFragment.newInstance(creditType)
            }

            2 -> {
                var movieListType = MovieListType(data = "rashmika mandanna",type = MovieListType.Type.PERSON);
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