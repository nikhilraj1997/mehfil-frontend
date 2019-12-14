package com.example.mehfil.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mehfil.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(home_toolbar)

        homeViewPagerAdapter =
            HomeViewPagerAdapter(
                supportFragmentManager,
                4
            )
        homeViewPagerAdapter.addFragment(MehfilFragment(), "Mehfil")
        homeViewPagerAdapter.addFragment(TuneFragment(), "Tune")
        homeViewPagerAdapter.addFragment(LearnFragment(), "Learn")
        homeViewPagerAdapter.addFragment(ProfileFragment(), "Profile")

        home_view_pager.adapter = homeViewPagerAdapter

        home_tab_layout.setupWithViewPager(home_view_pager)
    }
}
