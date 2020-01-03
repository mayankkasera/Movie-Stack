package com.example.moviestack.ui.common.credits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.ui.common.movielist.MovieListFragment
import com.example.moviestack.ui.common.movielist.MovieListType

class CreditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)

        var id = intent.getIntExtra("id",0)
        var movieListType = MovieListType(data = "${id}",type = MovieListType.Type.GENRE);
        replace(MovieListFragment.newInstance(movieListType))

    }

    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
