package com.example.moviestack.ui.common.movielist.paginglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.ui.common.movielist.MovieListType


class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        var id = intent.getIntExtra("id",0)
        val result: MovieListType.Type = intent.getSerializableExtra("type") as MovieListType.Type

        var creditType =
            MovieListType(
                data = "${id}",
                type = result
            );
        replace(
            MovieListPaggingFragment.newInstance(
                creditType
            )
        )
        
    }
    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
