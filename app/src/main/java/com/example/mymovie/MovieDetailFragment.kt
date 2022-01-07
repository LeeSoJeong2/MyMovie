package com.example.mymovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mymovie.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    private var likeCount = 0
    private var likeState = false
    private var unlikeCount = 0
    private var unlikeState = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        likeCount = (binding.likeText.text).toString().toInt()
        unlikeCount = (binding.unlikeText.text).toString().toInt()

        binding.backBtn.setOnClickListener{ backToMoviePager() }
        binding.likeBtn.setOnClickListener { likeBtnClicked() }
        binding.unlikeBtn.setOnClickListener { unlikeBtnClicked() }
        binding.showBtn.setOnClickListener{ showFullComment() }

        return binding.root
    }

    private fun backToMoviePager() {
        val fragmentManager: FragmentManager = parentFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MoviePagerFragment()

        transaction.replace(R.id.fragment_content, fragment, "MoviePager")
        transaction.commit()
    }

    private fun likeBtnClicked() {
        if (likeState) {
            decreaseLikeCount()
        } else {
            increaseLikeCount()
        }
        likeState = !likeState

        if (unlikeState) {
            decreaseUnlikeCount()
            unlikeState = !unlikeState
        }
    }

    private fun unlikeBtnClicked() {
        if (unlikeState) {
            decreaseUnlikeCount()
        } else {
            increaseUnlikeCount()
        }
        unlikeState = !unlikeState

        if (likeState) {
            decreaseLikeCount()
            likeState = !likeState
        }
    }

    private fun increaseLikeCount() {
        likeCount += 1
        binding.likeText.text = likeCount.toString()
        binding.likeBtn.setBackgroundResource(R.drawable.ic_thumb_up_selected)
    }

    private fun decreaseLikeCount() {
        likeCount -= 1
        binding.likeText.text = likeCount.toString()
        binding.likeBtn.setBackgroundResource(R.drawable.ic_thumb_up)
    }

    private fun increaseUnlikeCount() {
        unlikeCount += 1
        binding.unlikeText.text = unlikeCount.toString()
        binding.unlikeBtn.setBackgroundResource(R.drawable.ic_thumb_down_selected)
    }

    private fun decreaseUnlikeCount() {
        unlikeCount -= 1
        binding.unlikeText.text = unlikeCount.toString()
        binding.unlikeBtn.setBackgroundResource(R.drawable.ic_thumb_down)
    }


    private fun showFullComment() {
        val fragmentManager: FragmentManager = parentFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = FullCommentListFragment()
        transaction.replace(R.id.fragment_content, fragment,"FullCommentList")
        transaction.commit()
    }

}