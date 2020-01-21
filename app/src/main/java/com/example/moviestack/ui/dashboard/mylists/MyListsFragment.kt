package com.example.moviestack.ui.dashboard.mylists


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestack.R
import com.example.moviestack.ui.dashboard.mylists.adapter.MyListPagerAdapter
import kotlinx.android.synthetic.main.fragment_my_lists.view.*

/**
 * A simple [Fragment] subclass.
 */
class MyListsFragment : Fragment() {

    private lateinit var mView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_my_lists, container, false)

        mView.viewPager.adapter = MyListPagerAdapter(childFragmentManager)
        mView.viewPager.offscreenPageLimit = 2
        mView.tabLayout.setupWithViewPager(mView.viewPager)

        return mView
    }


}
