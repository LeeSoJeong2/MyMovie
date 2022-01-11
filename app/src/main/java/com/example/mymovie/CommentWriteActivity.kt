package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.mymovie.databinding.ActivityCommentWriteBinding

class CommentWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener { requestCreateComment() }
        binding.cancelButton.setOnClickListener{ finish() }

    }

    private fun requestCreateComment() {
        var url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/createComment"
        val contents = binding.contentsInput.text.toString()
        val rating = binding.ratingBar.rating
        url += "?id=${MainActivity.movieId}&writer=kym7112&rating=${rating}&contents=${contents}"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                Log.d("response Detail", "응답 받음 -> $it")
            },
            Response.ErrorListener {
                Log.d("response", "에러 발생 -> ${it.message}")
            }
        )
        request.setShouldCache(false)
        AppHelper.requestQueue.add(request)

        finish()
    }
}