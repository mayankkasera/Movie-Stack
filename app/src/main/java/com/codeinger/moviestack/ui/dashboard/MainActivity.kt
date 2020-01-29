package com.codeinger.moviestack.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.codeinger.moviestack.R
import com.codeinger.moviestack.ui.dashboard.home.HomeFragment
import com.codeinger.moviestack.ui.dashboard.menu.MenuFragment
import com.codeinger.moviestack.ui.dashboard.mylists.MyListsFragment
import com.codeinger.moviestack.ui.dashboard.search.SearchFragment
import com.codeinger.moviestack.ui.search.SearchActivity
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
                    replace(HomeFragment(),"home")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    replace(SearchFragment(),"search")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.featured_lists -> {
                    replace(MyListsFragment(),"mylist")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    replace(MenuFragment(),"setting")
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

    fun replace(fragment: Fragment?,backstack : String) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.addToBackStack(backstack)
        ft.replace(R.id.frameLayout, fragment!!)
        ft.commit()
    }

}
