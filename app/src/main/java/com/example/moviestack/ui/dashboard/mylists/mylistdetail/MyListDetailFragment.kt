package com.example.moviestack.ui.dashboard.mylists.mylistdetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.moviestack.R
import com.example.moviestack.databinding.MyListDetailFragmentDataBinding
import com.example.moviestack.pojo.MyList
import com.example.moviestack.roomdb.RoomDatabaseHelper
import com.example.moviestack.roomdb.mylist.MyListHelper
import com.example.moviestack.utils.createFactory
import kotlinx.android.synthetic.main.fragment_my_list_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class MyListDetailFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var type : MyList.Type
    private lateinit var viewModel: MyListDetailViewModel
    lateinit var binding : MyListDetailFragmentDataBinding

    companion object {
        private const val TYPE = "type"
        fun newInstance(type : MyList.Type): MyListDetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(TYPE,type)
            val fragment = MyListDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_list_detail, container, false)
        init()
        loadData()
        setObserver()

        mView.swipeContainer.setOnRefreshListener{
            mView.swipeContainer.isRefreshing = true
            loadData()
        }
        return mView
    }

    fun init(){
        mView = binding.getRoot()
        type = arguments?.getParcelable<MyList.Type>(MyListDetailFragment.TYPE) as MyList.Type
        val factory = MyListDetailViewModel(MyListHelper(RoomDatabaseHelper().localeDataBase)).createFactory()
        viewModel = ViewModelProvider(this, factory).get(MyListDetailViewModel::class.java)
    }

    private fun loadData() {
        viewModel.getResult(activity!!,type)
    }

    private fun setObserver() {
        viewModel.mutableLiveData.observe(this, Observer {
            binding.state = it
            mView.swipeContainer.isRefreshing = false
        })
    }

}
