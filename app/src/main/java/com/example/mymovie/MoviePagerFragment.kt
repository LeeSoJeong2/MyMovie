package com.example.mymovie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.mymovie.data.MovieInfo
import com.example.mymovie.data.MovieList
import com.example.mymovie.data.ResponseInfo
import com.example.mymovie.databinding.FragmentMoviePagerBinding
import com.google.gson.Gson

class MoviePagerFragment : Fragment(), ChangeMovieDetailFragment {
    private lateinit var binding: FragmentMoviePagerBinding
    private val movieAdapter = MoviePagerAdapter(context, this)
    private lateinit var movieInfo: MovieInfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviePagerBinding.inflate(inflater, container, false)
        binding.moviePager.adapter = movieAdapter

        requestMovieList()

        return binding.root
    }

    private fun requestMovieList() {
        var url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList"
        url += "?" + "type=1"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                Log.d("response", "응답 받음 -> $it")
                processMovieListResponse(it)
            },
            Response.ErrorListener {
                Log.d("response", "에러 발생 -> ${it.message}")
            }
        )
        request.setShouldCache(false)
        AppHelper.requestQueue.add(request)
    }

    private fun processMovieListResponse(response: String) {
        val gson = Gson()
        val info = gson.fromJson(response, ResponseInfo::class.java)

        if (info.code == 200) {
            val movieList = gson.fromJson(response, MovieList::class.java)
            initMoviePagerAdapter(movieList)
        }

    }

    fun initMoviePagerAdapter(movieList:MovieList) {
        for (i in 0 until movieList.result.size) {
            movieInfo = movieList.result[i]
            movieAdapter.addItem(MoviePosterItem("${i + 1}. ${movieInfo.title}", movieInfo.thumb, "예매율 ${movieInfo.reservation_rate}% | ${movieInfo.grade}세 관람가 | ${movieInfo.date}"))
            movieAdapter.notifyDataSetChanged()
        }
    }

    override fun detailBtnClick() {
        val fragmentManager: FragmentManager = parentFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MovieDetailFragment() // TODO: 어떤 영화를 클릭했는지 찾아서 연결
        transaction.replace(R.id.fragment_content, fragment, "MovieDetail")
        transaction.commit()
    }

}
