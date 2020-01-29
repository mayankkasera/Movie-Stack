package com.codeinger.moviestack.ui.dashboard.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codeinger.moviestack.R
import com.codeinger.moviestack.databinding.SearchFragmentDataBinding
import com.codeinger.moviestack.ui.dashboard.MainActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var binding : SearchFragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        (activity as MainActivity).bottomNavigationView.menu.getItem(1).isChecked = true

        init()


        return mView
    }

    fun init(){
        mView = binding.getRoot()
    }


}
