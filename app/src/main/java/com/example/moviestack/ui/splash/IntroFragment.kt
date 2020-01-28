package com.example.moviestack.ui.splash


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviestack.R
import com.example.moviestack.ui.common.fullimage.FullImageFragment
import kotlinx.android.synthetic.main.fragment_intro.view.*

/**
 * A simple [Fragment] subclass.
 */
class IntroFragment : Fragment() {

    lateinit var mView: View

    companion object {
        private const val TEXT = "text"
        private const val TITLE = "title"
        private const val IMAGE = "image"

        fun newInstance(text: String,title : String,image : Int): IntroFragment {
            val bundle = Bundle()
            bundle.putString(TEXT, text)
            bundle.putString(TITLE, title)
            bundle.putInt(IMAGE, image)
            val fragment = IntroFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_intro, container, false)

        var text= arguments?.getString(TEXT)!! as String
        var title = arguments?.getString(TITLE)!! as String
        var image= arguments?.getInt(IMAGE)!! as Int

        mView.title.text = title
        mView.description.text = text
        mView.slide_cover.setImageResource(image);

        return mView
    }


}
