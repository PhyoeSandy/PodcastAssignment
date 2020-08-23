package com.padcmyanmar.padcx.podcastassignment.activities

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.fragments.DownloadFragment
import com.padcmyanmar.padcx.podcastassignment.fragments.HomeFragment
import com.padcmyanmar.padcx.podcastassignment.fragments.ProfileFragment
import com.padcmyanmar.padcx.podcastassignment.fragments.SearchFragment
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, HomeFragment.newInstance("a","b"))
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener(
            object : BottomNavigationView.OnNavigationItemSelectedListener{

                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when(item.itemId){
                        R.id.action_home -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.flContainer,HomeFragment.newInstance("a","b"))
                                .commit()
                            return true
                        }
                        R.id.action_search -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.flContainer,SearchFragment.newInstance("a","b"))
                                .commit()
                            return true
                        }
                        R.id.action_download -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.flContainer,DownloadFragment.newInstance("a","b"))
                                .commit()
                            return true
                        }
                        R.id.action_profile -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.flContainer,ProfileFragment.newInstance("a","b"))
                                .commit()
                            return true
                        }
                    }
                    return false
                }
            })
    }
}