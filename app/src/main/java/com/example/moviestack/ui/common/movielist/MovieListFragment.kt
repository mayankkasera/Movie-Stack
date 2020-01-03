package com.example.moviestack.ui.common.movielist


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.moviestack.R
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.api.repo.movieInforepo.MovieRepository
import com.example.moviestack.api.repo.movieInforepo.MovieRepositoryI
import com.example.moviestack.databinding.SimilarFragmentBinding
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    private lateinit var id: String
    private lateinit var mView: View
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var binding: SimilarFragmentBinding
    private lateinit var movieRepositoryI: MovieRepositoryI

    companion object {
        private const val ID = "Id"
        fun newInstance(id : String): MovieListFragment {
            val bundle = Bundle()
            bundle.putSerializable(ID, id)
            val fragment =
                MovieListFragment()
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
        movieListViewModel.getSimilar()
    }


    private fun init() {
        id = arguments?.getSerializable(ID) as String
        movieRepositoryI = MovieRepository(NetworkHelper().gerMovieRequests(),id)
        mView = binding.getRoot()
        val factory = MovieListViewModel(
            movieRepositoryI
        ).createFactory()
        movieListViewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
    }

    private fun setObserver() {
        movieListViewModel.mutableLiveData.observe(this, Observer { binding.movieListState = it })
    }

}
