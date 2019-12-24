package com.example.moviestack.ui.dashboard.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviestack.R
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.api.repo.trendingmovieslist.TrendingMoviesRepository
import com.example.moviestack.api.repo.trendingmovieslist.TrendingMoviesRepositoryI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeFragment : Fragment() {

    private lateinit var mView: View
    private val trendingMoviesRepositoryI: TrendingMoviesRepositoryI = TrendingMoviesRepository(NetworkHelper().gerRetrofit())


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)

        trendingMoviesRepositoryI.getTrendingMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ v -> Log.i("xhcbvjd","Value is: ${v.toString()}") },
                { e -> Log.i("xhcbvjd","Error: ${e.message}")})


        return mView
    }


}
