package com.codeinger.moviestack.ui.common.fullimage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler
import com.codeinger.moviestack.R
import com.codeinger.moviestack.api.NetworkConstants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_full_image.view.*
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 */
class FullImageFragment : Fragment() {


    lateinit var mView: View

    companion object {
        private const val URL = "url"

        fun newInstance(url: String): FullImageFragment {
            val bundle = Bundle()
            bundle.putSerializable(URL, url)
            val fragment = FullImageFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_full_image, container, false)


        mView.loader.visibility = View.VISIBLE
        var url= arguments?.getSerializable(URL) as String


        Picasso.get()
            .load(NetworkConstants.baseImageOriginalUrl+url)
            .into(mView.image,
                object : Callback {
                    override fun onSuccess() {
                        mView.loader.visibility = View.GONE
                        mView.image.setOnTouchListener(ImageMatrixTouchHandler(context));
                    }
                    override fun onError(e: Exception?) {
                        mView. loader.visibility = View.GONE
                    }
                })


        return mView
    }


}
