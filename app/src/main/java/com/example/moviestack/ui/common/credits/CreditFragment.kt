package com.example.moviestack.ui.common.credits


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviestack.R
import com.example.moviestack.api.DataHelper
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.databinding.CastFragmentBinding
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class CreditFragment : Fragment() {

    private lateinit var id: String
    private lateinit var mView: View
    private lateinit var creditViewModel: CreditViewModel
    private lateinit var binding: CastFragmentBinding
    private lateinit var movieRepositoryI: MovieRepositoryI

    companion object {
        private const val ID = "Id"
        fun newInstance(id: String): CreditFragment {
            val bundle = Bundle()
            bundle.putSerializable(ID, id)
            val fragment = CreditFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cast, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
        creditViewModel.getCast(id)
    }


    private fun init() {
        id = arguments?.getSerializable(ID) as String
        movieRepositoryI = DataHelper().movieRepositoryI
        mView = binding.root
        val factory = CreditViewModel(movieRepositoryI).createFactory()
        creditViewModel = ViewModelProvider(this, factory).get(CreditViewModel::class.java)
    }

    private fun setObserver() {
        creditViewModel.mutableLiveData.observe(this, Observer {
            binding.castState = it
            Log.i("dkjhfckjds", "hdfsgd " + it)
        })
    }


}
