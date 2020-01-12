package com.example.moviestack.ui.common.movielist.paginglist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.moviestack.R
import com.example.moviestack.api.DataHelper
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.movie.MovieItemRepository
import com.example.moviestack.databinding.MovieLisPagingFragmentBinding
import com.example.moviestack.ui.common.movielist.MovieListType
import com.example.moviestack.ui.common.movielist.adapter.MovieListPagingAdapter
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class MovieListPaggingFragment : Fragment() {


    private lateinit var mView: View
    private lateinit var movieListType: MovieListType
    private lateinit var movieListPaggingViewModel: MovieListPaggingViewModel
    private lateinit var binding: MovieLisPagingFragmentBinding

    companion object {
        private const val ID = "Id"
        fun newInstance(movieListType: MovieListType): MovieListPaggingFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, movieListType)
            val fragment =
                MovieListPaggingFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list_pagging, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
        when (movieListType.type) {
            MovieListType.Type.TRENDING_MOVIE -> movieListPaggingViewModel.getTrendingData(DataHelper().trendingRepositoryI, SmallItemList.Type.TRENDING_MOVIES)
            MovieListType.Type.TRENDING_TV_SHOW -> movieListPaggingViewModel.getTrendingData(DataHelper().trendingRepositoryI, SmallItemList.Type.TRENDING_TV_SHOW)
            MovieListType.Type.NOW_PLAYING -> movieListPaggingViewModel.getMoviesData(MovieItemRepository(NetworkHelper().gerRetrofit()), SmallItemList.Type.NOW_PLAYING)
            MovieListType.Type.UPCOMING -> movieListPaggingViewModel.getMoviesData(MovieItemRepository(NetworkHelper().gerRetrofit()), SmallItemList.Type.UPCOMING)
            MovieListType.Type.POPULAR -> movieListPaggingViewModel.getMoviesData(MovieItemRepository(NetworkHelper().gerRetrofit()), SmallItemList.Type.POPULAR)
            MovieListType.Type.TOP_RATED -> movieListPaggingViewModel.getMoviesData(MovieItemRepository(NetworkHelper().gerRetrofit()), SmallItemList.Type.TOP_RATED)
            MovieListType.Type.SMILER -> movieListPaggingViewModel.getSimilarData(movieListType.data, DataHelper().movieRepositoryI)
            MovieListType.Type.GENRE -> movieListPaggingViewModel.getGenreData(movieListType.data, DataHelper().discoverRepositoryI)
            MovieListType.Type.MOVIE_SEARCH -> movieListPaggingViewModel.getSearchMovieData(movieListType.data, DataHelper().searchRepositoryI,SmallItemList.Type.SEARCH_MOVIES)
            MovieListType.Type.TV_SEARCH -> movieListPaggingViewModel.getSearchMovieData(movieListType.data, DataHelper().searchRepositoryI,SmallItemList.Type.SEARCH_TV)
        }
    }

    private fun init() {
        movieListType = arguments?.getParcelable<MovieListType>(
            ID
        ) as MovieListType
        mView = binding.root
        val factory = MovieListPaggingViewModel()
            .createFactory()
        movieListPaggingViewModel = ViewModelProvider(this, factory).get(MovieListPaggingViewModel::class.java)
    }

    private fun setObserver() {
        binding.adapter = MovieListPagingAdapter()
        movieListPaggingViewModel.state.observe(this, Observer { binding.movieListState = it })
        movieListPaggingViewModel.moviePagedList.observe(this, Observer { binding!!.adapter!!.submitList(it!!) })
    }


}
