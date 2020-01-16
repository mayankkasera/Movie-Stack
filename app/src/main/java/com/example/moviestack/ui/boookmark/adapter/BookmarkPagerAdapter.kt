package com.example.moviestack.ui.boookmark.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.simplelist.MovieListFragment

class BookmarkPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when(position){
            0,2,1 -> {
                var listType = ListType(
                    data = "${0}",
                    type = ListType.Type.BOOKMARK_MOVIE
                )
                MovieListFragment.newInstance(listType)
            }
            else -> {
                var listType = ListType(
                    data = "${0}",
                    type = ListType.Type.BOOKMARK_MOVIE
                )
                MovieListFragment.newInstance(listType)
            }

        }

    }

    override fun getCount(): Int {
          return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->  "Movie"
            1 ->  "Tv Show"
            2 ->  "Person"
            else -> ""
        }
    }



}