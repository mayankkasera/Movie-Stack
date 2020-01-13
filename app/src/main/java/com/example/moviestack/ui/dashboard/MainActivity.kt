package com.example.moviestack.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.moviestack.R
import com.example.moviestack.ui.dashboard.search.SearchFragment
import com.example.moviestack.ui.dashboard.featuredlists.FeaturedListsFragment
import com.example.moviestack.ui.dashboard.home.HomeFragment
import com.example.moviestack.ui.dashboard.settings.SettingsFragment
import com.example.moviestack.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        initBottomNavigation()
        replace(HomeFragment())

        search.setOnClickListener{
              var intent = Intent(this@MainActivity,SearchActivity::class.java)
              startActivity(intent)
        }

    }

    private fun initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    replace(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    replace(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.featured_lists -> {
                    replace(FeaturedListsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    replace(SettingsFragment())
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
