package com.example.moviestack.ui.common.credits.paging


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
import com.example.moviestack.api.repo.smallitemlist.SmallItemRepository
import com.example.moviestack.databinding.PersonPagingFragmentBinding
import com.example.moviestack.ui.common.credits.CreditType
import com.example.moviestack.ui.common.credits.adapter.PersonPagingAdapter
import com.example.moviestack.ui.common.credits.simple.CreditFragment
import com.example.moviestack.ui.common.movielist.MovieListType
import com.example.moviestack.ui.common.movielist.adapter.MovieListPagingAdapter
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingViewModel
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class PersonPagingFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var movieListType: MovieListType
    private lateinit var personViewModel: PersonViewModel
    private lateinit var binding: PersonPagingFragmentBinding

    companion object {
        private const val ID = "Id"
        fun newInstance(movieListType: MovieListType): PersonPagingFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, movieListType)
            val fragment =
                PersonPagingFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_paging, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }


    private fun loadData() {
        personViewModel.getSearchMovieData(movieListType.data, DataHelper().searchRepositoryI,
            SmallItemList.Type.PERSON)
    }

    private fun init() {
        movieListType = arguments?.getParcelable<MovieListType>(
            PersonPagingFragment.ID
        ) as MovieListType
        mView = binding.root
        val factory = PersonViewModel()
            .createFactory()
        personViewModel = ViewModelProvider(this, factory).get(PersonViewModel::class.java)
    }

    private fun setObserver() {
        binding.adapter = PersonPagingAdapter()
        personViewModel.state.observe(this, Observer { binding.movieListState = it })
        personViewModel.moviePagedList.observe(this, Observer { binding!!.adapter!!.submitList(it!!) })
    }

}
