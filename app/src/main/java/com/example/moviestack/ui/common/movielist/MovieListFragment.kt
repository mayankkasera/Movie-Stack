package com.example.moviestack.ui.common.movielist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviestack.R
import com.example.moviestack.api.DataHelper
import com.example.moviestack.databinding.SimilarFragmentBinding
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    private lateinit var movieListType: MovieListType
    private lateinit var mView: View
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var binding: SimilarFragmentBinding

    companion object {
        private const val ID = "Id"
        fun newInstance(movieListType: MovieListType): MovieListFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, movieListType)
            val fragment = MovieListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_similar, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }


    private fun loadData() {
        when (movieListType.type) {
            MovieListType.Type.SMILER -> movieListViewModel.getMovieList(movieListType.data)
            MovieListType.Type.GENRE -> movieListViewModel.getGenreMovieList(movieListType.data)
        }
    }


    private fun init() {
        movieListType = arguments?.getParcelable<MovieListType>(ID) as MovieListType
        mView = binding.root
        val factory = MovieListViewModel(
            DataHelper().discoverRepositoryI,
            DataHelper().movieRepositoryI
        ).createFactory()
        movieListViewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
    }

    private fun setObserver() {
        movieListViewModel.mutableLiveData.observe(this, Observer { binding.movieListState = it })
    }

}
