package com.example.moviestack.ui.common.movielist.simplelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingFragment
import com.example.moviestack.ui.common.ListType

class ShowMoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_more)

        var id = intent.getIntExtra("id",0)
        var creditType = ListType(
            data = "${id}",
            type = ListType.Type.TRENDING_MOVIE
        );
        replace(MovieListPaggingFragment.newInstance(creditType))

    }

    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
