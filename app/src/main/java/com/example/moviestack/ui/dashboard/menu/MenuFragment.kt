package com.example.moviestack.ui.dashboard.menu


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.moviestack.BuildConfig
import com.example.moviestack.R
import com.example.moviestack.databinding.ShareFregmentBinding
import com.example.moviestack.ui.aboutas.AboutAsActivity
import com.example.moviestack.ui.boookmark.BookamrkActivity
import com.example.moviestack.ui.dashboard.MainActivity
import com.example.moviestack.utils.setAllOnClickListener
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var binding : ShareFregmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as MainActivity).bottomNavigationView.menu.getItem(3).isChecked = true

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        init()


        binding.bookmarkGroup.setAllOnClickListener(View.OnClickListener {
            startActivity(Intent(context, BookamrkActivity::class.java))
        })

        binding.rateGroup.setAllOnClickListener(View.OnClickListener {
            val uri: Uri = Uri.parse("market://details?id=" + context!!.packageName)
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)

            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + context!!.packageName)
                    )
                )
            }
        })

        binding.shareGroup.setAllOnClickListener(View.OnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey check out Movie Stack at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        })

        binding.aboutAsGroup.setAllOnClickListener(View.OnClickListener {
            startActivity(Intent(context,AboutAsActivity::class.java))
        })

        return mView
    }

    fun init(){
        mView = binding.getRoot()
    }



}
