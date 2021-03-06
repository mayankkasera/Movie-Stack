package com.codeinger.moviestack.ui.persondetail.info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.codeinger.moviestack.R
import com.codeinger.moviestack.api.DataHelper
import com.codeinger.moviestack.api.repo.person.PersonRepositoryI
import com.codeinger.moviestack.databinding.PersonInfoBinding
import com.codeinger.moviestack.utils.createFactory


class PersonInfoFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var id: String
    private lateinit var infoViewModel: PersonInfoViewModel
    private lateinit var binding: PersonInfoBinding
    private lateinit var personInfoRepositoryI: PersonRepositoryI



    companion object {
        private const val ID = "Id"
        fun newInstance(id: String): PersonInfoFragment {
            val bundle = Bundle()
            bundle.putSerializable(ID, id)
            val fragment = PersonInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_info, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
        infoViewModel.loadData(id)
    }

    private fun init() {
        id = arguments?.getSerializable(PersonInfoFragment.ID) as String
        personInfoRepositoryI = DataHelper().personRepositoryI
        mView = binding.root

        val factory = PersonInfoViewModel(personInfoRepositoryI).createFactory()
        infoViewModel = ViewModelProvider(this, factory).get(PersonInfoViewModel::class.java)
    }

    private fun setObserver() {
        infoViewModel.mutableLiveData.observe(this, Observer {
            binding.personInfoState = it
            Log.i("bvhvhg","${it.personImageAdapter}")
        })
    }

}
