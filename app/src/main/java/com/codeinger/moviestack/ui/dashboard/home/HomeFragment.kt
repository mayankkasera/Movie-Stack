package com.codeinger.moviestack.ui.dashboard.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codeinger.moviestack.R
import com.codeinger.moviestack.api.DataHelper
import com.codeinger.moviestack.databinding.HomeDataBinding
import com.codeinger.moviestack.ui.dashboard.MainActivity
import com.codeinger.moviestack.utils.createFactory
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        (activity as MainActivity).bottomNavigationView.menu.getItem(0).isChecked = true
        init()
        loadData()
        setObserver()
        return mView

    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun init() {
        mView = binding.getRoot()
        val factory = HomeViewModel(
            DataHelper().movieItemRepositoryI,
            DataHelper().trendingRepositoryI,
            DataHelper().tvShowRepositoryI
        ).createFactory()
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    private fun setObserver() {
        viewModel.mutableLiveData.observe(this, Observer { binding.homeState = it })
    }



}
