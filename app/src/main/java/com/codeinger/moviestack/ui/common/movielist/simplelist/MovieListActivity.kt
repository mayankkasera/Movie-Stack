package com.codeinger.moviestack.ui.common.movielist.simplelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.codeinger.moviestack.R
import com.codeinger.moviestack.ui.common.ListType

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_more)
        var  data :ListType = intent.getParcelableExtra<ListType>("list_type")
        replace(MovieListFragment.newInstance(data))
    }

    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }


}
