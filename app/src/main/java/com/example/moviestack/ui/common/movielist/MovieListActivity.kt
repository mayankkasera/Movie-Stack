package com.example.moviestack.ui.common.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.ui.common.credits.CreditFragment
import com.example.moviestack.ui.common.credits.CreditType
import com.example.moviestack.ui.dashboard.home.HomeFragment

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        var id = intent.getIntExtra("id",0)
        var creditType = MovieListType(data = "${id}",type =MovieListType.Type.GENRE );
        replace(MovieListFragment.newInstance(creditType))
        
    }
    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
