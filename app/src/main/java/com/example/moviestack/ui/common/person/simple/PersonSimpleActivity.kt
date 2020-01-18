package com.example.moviestack.ui.common.person.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.moviestack.R
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.moviedetail.DetailData

class PersonSimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)


        var id = intent.getLongExtra("id",0)
        var type = intent.getParcelableExtra("type") as DetailData.Type
        var creditType = ListType(
            data = "${id}",
            type = ListType.Type.CREW
        );
        replace(
            PersonSimpleFragment.newInstance(creditType,type)
        )
    }

    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}
