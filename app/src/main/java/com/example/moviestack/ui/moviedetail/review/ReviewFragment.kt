package com.example.moviestack.ui.moviedetail.review


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
import com.example.moviestack.api.DataHelper
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.databinding.ReviewtFragmentBinding
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.info.InfoFragment
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : Fragment() {

    private lateinit var id: String
    private lateinit var mView: View
    private lateinit var type : DetailData.Type
    private lateinit var reviewViewModel: ReviewViewModel
    private lateinit var binding: ReviewtFragmentBinding


    companion object {
        private const val ID = "Id"
        private const val TYPE = "type"
        fun newInstance(id : String,type : DetailData.Type): ReviewFragment {
            val bundle = Bundle()
            bundle.putSerializable(ID, id)
            bundle.putParcelable(TYPE,type)
            val fragment = ReviewFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
        when(type){
            DetailData.Type.MOVIE ->reviewViewModel.getReview(id,DataHelper().movieItemRepositoryI)
                DetailData.Type.TV_SHOW ->reviewViewModel.getReview(id,DataHelper().tvShowRepositoryI)
        }

    }


    private fun init() {
        type = arguments?.getParcelable<DetailData.Type>(TYPE) as DetailData.Type
        id = arguments?.getSerializable(ReviewFragment.ID) as String
        mView = binding.getRoot()
        val factory = ReviewViewModel().createFactory()
        reviewViewModel = ViewModelProvider(this, factory).get(ReviewViewModel::class.java)
    }

    private fun setObserver() {
        reviewViewModel.mutableLiveData.observe(this, Observer { binding.reviewState = it
            Log.i("dkjhfckjds","hdfsgd "+it)
        })
    }



}
