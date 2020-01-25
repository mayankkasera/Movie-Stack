package com.example.moviestack.ui.common.person.paging


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
import com.example.moviestack.pojo.SmallItemList
import com.example.moviestack.databinding.PersonPagingFragmentBinding
import com.example.moviestack.ui.common.person.adapter.PersonPagingAdapter
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingFragment
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class PersonPagingFragment : Fragment() {

    private lateinit var mView: View
    var type : DetailData.Type? = null
    private lateinit var listType: ListType
    private lateinit var personPagingViewModel: PersonPagingViewModel
    private lateinit var binding: PersonPagingFragmentBinding

    companion object {
        private const val ID = "Id"
        private const val TYPE = "type"

        fun newInstance(listType: ListType): PersonPagingFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, listType)
            val fragment = PersonPagingFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(listType: ListType,type : DetailData.Type): PersonPagingFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, listType)
            bundle.putParcelable(TYPE,type)
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
        when(listType.type){
            ListType.Type.PERSON_SEARCH -> personPagingViewModel.getSearchMovieData(listType.data, DataHelper().searchRepositoryI, SmallItemList.Type.PERSON)
            ListType.Type.POPULAR_PERSON -> personPagingViewModel.getPopularPersonData(DataHelper().personRepositoryI)
            ListType.Type.TRENDING_PERSON -> personPagingViewModel.getTrendingData(DataHelper().trendingRepositoryI, SmallItemList.Type.TRENDING_PERSON)
        }

    }

    private fun init() {
        listType = arguments?.getParcelable<ListType>(ID) as ListType
        type = arguments?.getParcelable<DetailData.Type>(TYPE) as? DetailData.Type
        mView = binding.root
        val factory = PersonPagingViewModel().createFactory()
        personPagingViewModel = ViewModelProvider(this, factory).get(PersonPagingViewModel::class.java)
    }

    private fun setObserver() {
        binding.adapter = PersonPagingAdapter()
        personPagingViewModel.state.observe(this, Observer { binding.movieListState = it })
        personPagingViewModel.moviePagedList.observe(this, Observer { binding!!.adapter!!.submitList(it!!) })
    }

}
