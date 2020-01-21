package com.example.moviestack.ui.dashboard.settings


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.databinding.ShareFregmentBinding
import com.example.moviestack.ui.boookmark.BookamrkActivity

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var binding : ShareFregmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        init()


        binding.bookmarkGroup.setAllOnClickListener(View.OnClickListener {
            startActivity(Intent(context, BookamrkActivity::class.java))
        })

        binding.rateGroup.setAllOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Rate Group", Toast.LENGTH_SHORT).show()
        })

        binding.shareGroup.setAllOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Share Group", Toast.LENGTH_SHORT).show()
        })

        binding.aboutAsGroup.setAllOnClickListener(View.OnClickListener {
            Toast.makeText(context,"About As Group", Toast.LENGTH_SHORT).show()
        })

        return mView
    }

    fun init(){
        mView = binding.getRoot()
    }

    fun Group.setAllOnClickListener(listener: View.OnClickListener) {
        referencedIds.forEach { id ->
            rootView.findViewById<View>(id).setOnClickListener(listener)
        }
    }

}
