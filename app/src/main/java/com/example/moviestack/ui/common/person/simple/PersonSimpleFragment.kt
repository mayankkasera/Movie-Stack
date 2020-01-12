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
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.databinding.CastFragmentBinding
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class PersonSimpleFragment : Fragment() {


    private lateinit var mView: View
    private lateinit var listType: ListType
    private lateinit var personSimpleViewModel: PersonSimpleViewModel
    private lateinit var binding: CastFragmentBinding
    private lateinit var movieRepositoryI: MovieRepositoryI

    companion object {
        private const val ID = "Id"
        fun newInstance(listType: ListType): PersonSimpleFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, listType)
            val fragment =
                PersonSimpleFragment()
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
        when (listType.type) {
            ListType.Type.CAST -> personSimpleViewModel.getCast(listType.data)
            ListType.Type.CREW -> personSimpleViewModel.getCrew(listType.data)
        }
    }


    private fun init() {
        listType = arguments?.getParcelable<ListType>(
            ID
        ) as ListType
        movieRepositoryI = DataHelper().movieRepositoryI
        mView = binding.root
        val factory = PersonSimpleViewModel(
            movieRepositoryI
        ).createFactory()
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
