package com.example.mymovie


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MoviePagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
    val fragments: List<Fragment>
    init {
        fragments = listOf(MovieFragment1(), MovieFragment2(), MovieFragment3(), MovieFragment4(), MovieFragment5(), MovieFragment6())
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}