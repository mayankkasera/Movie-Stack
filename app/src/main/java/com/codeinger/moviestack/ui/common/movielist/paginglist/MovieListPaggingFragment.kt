package com.codeinger.moviestack.ui.common.movielist.paginglist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.codeinger.moviestack.R
import com.codeinger.moviestack.api.DataHelper
import com.codeinger.moviestack.pojo.SmallItemList
import com.codeinger.moviestack.databinding.MovieLisPagingFragmentBinding
import com.codeinger.moviestack.ui.common.ListType
import com.codeinger.moviestack.ui.common.movielist.adapter.MovieListPagingAdapter
import com.codeinger.moviestack.ui.moviedetail.DetailData
import com.codeinger.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class MovieListPaggingFragment : Fragment() {


    private lateinit var mView: View
    private lateinit var listType: ListType
    var type : DetailData.Type? = null
    private lateinit var movieListPaggingViewModel: MovieListPaggingViewModel
    private lateinit var binding: MovieLisPagingFragmentBinding

    companion object {
        private const val ID = "Id"
        private const val TYPE = "type"
        fun newInstance(listType: ListType,type : DetailData.Type): MovieListPaggingFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, listType)
            bundle.putParcelable(TYPE,type)
            val fragment =
                MovieListPaggingFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(listType: ListType): MovieListPaggingFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, listType)
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
        when (listType.type) {
            ListType.Type.TRENDING_MOVIE -> movieListPaggingViewModel.getTrendingData(DataHelper().trendingRepositoryI, SmallItemList.Type.TRENDING_MOVIES)
            ListType.Type.TRENDING_TV_SHOW -> movieListPaggingViewModel.getTrendingData(DataHelper().trendingRepositoryI, SmallItemList.Type.TRENDING_TV_SHOW)
            ListType.Type.NOW_PLAYING -> movieListPaggingViewModel.getMoviesData(DataHelper().movieItemRepositoryI, SmallItemList.Type.NOW_PLAYING)
            ListType.Type.UPCOMING -> movieListPaggingViewModel.getMoviesData(DataHelper().movieItemRepositoryI, SmallItemList.Type.UPCOMING)
            ListType.Type.POPULAR -> movieListPaggingViewModel.getMoviesData(DataHelper().movieItemRepositoryI, SmallItemList.Type.POPULAR_MOVIES)
            ListType.Type.POPULAR_TV_SHOW -> movieListPaggingViewModel.getMoviesData(DataHelper().movieItemRepositoryI, SmallItemList.Type.POPULAR_TV_SHOW)
            ListType.Type.TOP_RATED -> movieListPaggingViewModel.getMoviesData(DataHelper().movieItemRepositoryI, SmallItemList.Type.TOP_RATED_MOVIES)
            ListType.Type.TOP_RATED_TV_SHOW -> movieListPaggingViewModel.getTvShowData(DataHelper().tvShowRepositoryI, SmallItemList.Type.TOP_RATED_TV_SHOW)
            ListType.Type.SMILER -> {

                if(type==DetailData.Type.MOVIE)
                    movieListPaggingViewModel.getSimilarData(listType.data, DataHelper().movieItemRepositoryI)
                else if(type==DetailData.Type.TV_SHOW)
                    movieListPaggingViewModel.getSimilarData(listType.data,DataHelper().tvShowRepositoryI)

            }
            ListType.Type.GENRE -> movieListPaggingViewModel.getGenreData(listType.data, DataHelper().discoverRepositoryI)
            ListType.Type.MOVIE_SEARCH -> movieListPaggingViewModel.getSearchMovieData(listType.data, DataHelper().searchRepositoryI, SmallItemList.Type.SEARCH_MOVIES)
            ListType.Type.TV_SEARCH -> movieListPaggingViewModel.getSearchMovieData(listType.data, DataHelper().searchRepositoryI, SmallItemList.Type.SEARCH_TV)
        }
    }

    private fun init() {
        listType = arguments?.getParcelable<ListType>(ID) as ListType
        type = arguments?.getParcelable<DetailData.Type>(TYPE) as? DetailData.Type

        mView = binding.root
        val factory = MovieListPaggingViewModel()
            .createFactory()
        movieListPaggingViewModel = ViewModelProvider(this, factory).get(MovieListPaggingViewModel::class.java)
    }

    private fun setObserver() {
        binding.adapter = MovieListPagingAdapter(type!!)
        movieListPaggingViewModel.state.observe(this, Observer { binding.movieListState = it })
        movieListPaggingViewModel.moviePagedList.observe(this, Observer { binding!!.adapter!!.submitList(it!!) })
    }


}
