package com.example.moviestack.ui.common.movielist.paginglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.moviedetail.DetailData
import kotlinx.android.synthetic.main.crew.*


class MovieListPaggingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        var id = intent.getIntExtra("id",0)
        val result: ListType.Type = intent.getSerializableExtra("type") as ListType.Type
        val type: DetailData.Type = intent.getParcelableExtra("detailType") as DetailData.Type

        var creditType =
            ListType(
                data = "${id}",
                type = result
            );
        replace(
            MovieListPaggingFragment.newInstance(
                creditType,
                type
            )
        )
        
    }
    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
