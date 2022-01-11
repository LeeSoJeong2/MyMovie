package com.example.mymovie

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.mymovie.data.CommentList
import com.example.mymovie.data.MovieDetailList
import com.example.mymovie.data.MovieList
import com.example.mymovie.data.ResponseInfo
import com.example.mymovie.databinding.FragmentMovieDetailBinding
import com.google.gson.Gson

class MovieDetailFragment() : Fragment() {
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

        requestMovieDetail() // 서버에 영화정보 요청


        binding.backBtn.setOnClickListener{ backToMoviePager() }
        binding.likeBtn.setOnClickListener { likeBtnClicked() }
        binding.unlikeBtn.setOnClickListener { unlikeBtnClicked() }
        binding.showBtn.setOnClickListener{ showFullComment() }

        return binding.root
    }

    private fun requestMovieDetail() {
        var url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovie"
        url += "?id=${MainActivity.movieId}"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                Log.d("response Detail", "응답 받음 -> $it")
                processMovieDetailResponse(it)
            },
            Response.ErrorListener {
                Log.d("response", "에러 발생 -> ${it.message}")
            }
        )
        request.setShouldCache(false)
        AppHelper.requestQueue.add(request)
    }

    private fun processMovieDetailResponse(response: String) {
        val gson = Gson()
        val info = gson.fromJson(response, ResponseInfo::class.java)

        if (info.code == 200) {
            val movieDetailList = gson.fromJson(response, MovieDetailList::class.java)
            setMovieDetailInfo(movieDetailList)
        }
    }

    private fun setMovieDetailInfo(movieDetailList: MovieDetailList) {
        val movieDetail = movieDetailList.result[0]
        val task = ImageLoadTask(movieDetail.image, binding.posterImg)
        task.execute()
        binding.titleText.text = movieDetail.title
        binding.dateText.text = "${movieDetail.date} 개봉"
        binding.genreText.text = "${movieDetail.genre} / ${movieDetail.duration}분"
        likeCount = movieDetail.like
        binding.likeText.text = (movieDetail.like).toString()
        unlikeCount = movieDetail.dislike
        binding.unlikeText.text = (movieDetail.dislike).toString()
        binding.reservationRate.text = "${movieDetail.reservation_grade}위 ${movieDetail.reservation_rate}%"
        binding.ratingBar.rating = movieDetail.audience_rating
        binding.ratingText.text = (movieDetail.audience_rating).toString()
        binding.audience.text = "${movieDetail.audience}명"
        binding.summaryText.text = movieDetail.synopsis
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