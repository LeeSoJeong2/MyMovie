package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList

class MoviePagerActivity : AppCompatActivity() {
    var pager: ViewPager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_pager)

        pager = findViewById<View>(R.id.pager) as ViewPager
        pager!!.offscreenPageLimit = 6

        val adapter = MoviePagerAdapter(supportFragmentManager)

        val fragment1 = MovieFragment1()
        adapter.addItem(fragment1)

        val fragment2 = MovieFragment2()
        adapter.addItem(fragment2)

        val fragment3 = MovieFragment3()
        adapter.addItem(fragment3)

        val fragment4 = MovieFragment4()
        adapter.addItem(fragment4)

        val fragment5 = MovieFragment5()
        adapter.addItem(fragment5)

        val fragment6 = MovieFragment6()
        adapter.addItem(fragment6)

        pager!!.adapter = adapter


    }

    internal class MoviePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        var items = ArrayList<Fragment>()
        fun addItem(item: Fragment) {
            items.add(item)
        }

        override fun getItem(position: Int): Fragment {
            return items[position]
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "페이지 $position"
        }
    }




}