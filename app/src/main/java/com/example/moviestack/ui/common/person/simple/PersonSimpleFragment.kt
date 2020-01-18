package com.example.moviestack.ui.common.person.simple


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
import com.example.moviestack.databinding.CastFragmentBinding
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class PersonSimpleFragment : Fragment() {


    private lateinit var mView: View
    private lateinit var listType: ListType
    private lateinit var type : DetailData.Type
    private lateinit var personSimpleViewModel: PersonSimpleViewModel
    private lateinit var binding: CastFragmentBinding


    companion object {
        private const val ID = "id"
        private const val TYPE = "type"
        fun newInstance(listType: ListType,detailDataType : DetailData.Type): PersonSimpleFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, listType)
            bundle.putParcelable(TYPE,detailDataType)
            val fragment = PersonSimpleFragment()
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
        if(type==DetailData.Type.MOVIE){
            when (listType.type) {
                ListType.Type.CAST -> personSimpleViewModel.getMovieCast(listType.data,DataHelper().movieItemRepositoryI)
                ListType.Type.CREW -> personSimpleViewModel.getMovieCrew(listType.data,DataHelper().movieItemRepositoryI)
            }
        }
        else{
            when (listType.type) {
                ListType.Type.CAST -> personSimpleViewModel.getTvCast(listType.data,DataHelper().tvShowRepositoryI)
                ListType.Type.CREW -> personSimpleViewModel.getTvCrew(listType.data,DataHelper().tvShowRepositoryI)
            }
        }
    }


    private fun init() {
        listType = arguments?.getParcelable<ListType>(ID) as ListType
        type = arguments?.getParcelable<DetailData.Type>(TYPE) as DetailData.Type

        mView = binding.root
        val factory = PersonSimpleViewModel().createFactory()
        personSimpleViewModel = ViewModelProvider(this, factory).get(PersonSimpleViewModel::class.java)
    }

    private fun setObserver() {
        personSimpleViewModel.mutableLiveData.observe(this, Observer {
            binding.castState = it
            Log.i("dsnkjn","bcjsdb"+it.toString())
            binding.cast.adapter = it.personAdapter
        })
    }


}
