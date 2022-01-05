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
import com.example.mymovie.databinding.ActivityMainBinding
import com.example.mymovie.databinding.ActivityMoviePagerBinding
import java.util.ArrayList

class MoviePagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMoviePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = MoviePagerAdapter(this)
    }

}