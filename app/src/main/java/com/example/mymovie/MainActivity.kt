package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mymovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 획득
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 액티비티 화면 출력
        setContentView(binding.root)


        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MoviePagerFragment()
        transaction.add(R.id.fragment_content, fragment, "MoviePager")
        transaction.commit()

    }





}