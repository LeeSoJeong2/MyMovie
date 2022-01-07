package com.example.mymovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mymovie.databinding.FragmentMoviePagerBinding


class MoviePagerFragment : Fragment(), ChangeMovieDetailFragment {
    private lateinit var binding: FragmentMoviePagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviePagerBinding.inflate(inflater, container, false)
        binding.moviePager.adapter = MoviePagerAdapter(context, this)

        return binding.root
    }

    override fun detailBtnClick() {
        val fragmentManager: FragmentManager = parentFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MovieDetailFragment()
        transaction.replace(R.id.fragment_content, fragment, "MovieDetail")
        transaction.commit()
    }

}

interface ChangeMovieDetailFragment {
    fun detailBtnClick()
}