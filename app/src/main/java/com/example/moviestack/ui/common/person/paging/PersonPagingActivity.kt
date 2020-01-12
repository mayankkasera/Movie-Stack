package com.example.moviestack.ui.common.person.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.person.simple.PersonSimpleFragment

class PersonPagingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_paging)


        var listType = ListType(
            data = "",
            type = ListType.Type.POPULAR_PERSON
        );
        replace(
            PersonPagingFragment.newInstance(
                listType
            )
        )

    }

    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
