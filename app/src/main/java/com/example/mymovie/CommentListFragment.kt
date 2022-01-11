package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.mymovie.data.CommentList
import com.example.mymovie.data.ResponseInfo
import com.example.mymovie.databinding.FragmentCommentListBinding
import com.google.gson.Gson


class CommentListFragment() : Fragment() {
    private lateinit var binding: FragmentCommentListBinding
    private val commentAdapter = CommentAdapter(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentListBinding.inflate(inflater, container, false)

        binding.commentView.adapter = commentAdapter // 리사이클러뷰에 어댑터 연결
        requestCommentList() // 서버에 한줄평 정보 요청

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.commentView.layoutManager = layoutManager

        // 한줄평 작성하기
        binding.writeBtn.setOnClickListener{ showCommentWrite() }

       return binding.root
    }

    private fun requestCommentList() {
        var url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList"
        url += "?id=${MainActivity.movieId}"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                Log.d("response Detail", "응답 받음 -> $it")
                processCommentListResponse(it)
            },
            Response.ErrorListener {
                Log.d("response", "에러 발생 -> ${it.message}")
            }
        )
        request.setShouldCache(false)
        AppHelper.requestQueue.add(request)
    }

    private fun processCommentListResponse(response:String) {
        val gson = Gson()
        val info = gson.fromJson(response, ResponseInfo::class.java)

        if (info.code == 200) {
            val commentList = gson.fromJson(response, CommentList::class.java)
            setCommentInfo(commentList)
        }
    }

    fun setCommentInfo(commentList: CommentList){
        for (comment in commentList.result) {
            commentAdapter.addItem(CommentItem(comment.writer, comment.time, comment.rating, comment.contents, comment.recommend))
            commentAdapter.notifyDataSetChanged()
        }
    }


    private fun showCommentWrite() {
        val intent = Intent(context, CommentWriteActivity::class.java)
        startActivity(intent)
        //commentAdapter.notifyDataSetChanged()
    }

}