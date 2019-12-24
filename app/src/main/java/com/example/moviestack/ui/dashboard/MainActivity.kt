package com.example.moviestack.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.moviestack.R
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.ui.dashboard.category.CategoryFragment
import com.example.moviestack.ui.dashboard.featuredlists.FeaturedListsFragment
import com.example.moviestack.ui.dashboard.home.HomeFragment
import com.example.moviestack.ui.dashboard.papularpeople.PapularPeopleFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavigation()

        Log.i("jhdvs", NetworkHelper().gerRetrofit().toString())

    }

    private fun initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    replace(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.category -> {
                    replace(CategoryFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.featured_lists -> {
                    replace(FeaturedListsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.papular_people -> {
                    replace(PapularPeopleFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }

            }
        }
    }

    fun replace(fragment: Fragment?) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frameLayout, fragment!!)
        ft.commit()
    }

}
