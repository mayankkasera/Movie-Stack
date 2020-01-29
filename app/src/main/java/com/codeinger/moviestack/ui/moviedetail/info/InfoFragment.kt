package com.codeinger.moviestack.ui.moviedetail.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codeinger.moviestack.R
import com.codeinger.moviestack.api.DataHelper
import com.codeinger.moviestack.databinding.InfoFragmentBinding
import com.codeinger.moviestack.roomdb.RoomDatabaseHelper
import com.codeinger.moviestack.ui.moviedetail.DetailData
import com.codeinger.moviestack.utils.createFactory


class InfoFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var id: String
    private lateinit var type : DetailData.Type
    private lateinit var infoViewModel: InfoViewModel
    private lateinit var binding: InfoFragmentBinding


    companion object {
        private const val ID = "Id"
        private const val TYPE = "type"
        fun newInstance(id: String,type : DetailData.Type): InfoFragment {
            val bundle = Bundle()
            bundle.putSerializable(ID, id)
            bundle.putParcelable(TYPE,type)
            val fragment = InfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
         when(type){
             DetailData.Type.MOVIE ->  infoViewModel.getMovie(id,DataHelper().movieItemRepositoryI,type)
             DetailData.Type.TV_SHOW ->  infoViewModel.getTvShow(id,DataHelper().tvShowRepositoryI,type)
         }
    }

    private fun init() {
        id = arguments?.getSerializable(ID) as String
        type = arguments?.getParcelable<DetailData.Type>(TYPE) as DetailData.Type

        Log.i("sachsd","$type")

        mView = binding.root
        val factory = InfoViewModel().createFactory()
        infoViewModel = ViewModelProvider(this, factory).get(InfoViewModel::class.java)
    }

    private fun setObserver() {
        infoViewModel.mutableLiveData.observe(this, Observer {
            binding.infoState = it
            binding.type = type
            binding.localDatabase = RoomDatabaseHelper().localeDataBase

        })
    }


}
