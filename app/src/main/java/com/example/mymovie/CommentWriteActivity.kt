package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import com.example.mymovie.databinding.ActivityCommentListBinding
import com.example.mymovie.databinding.ActivityCommentWriteBinding

class CommentWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCommentWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener { saveComment(binding.contentsInput, binding.ratingBar) }

        binding.cancelButton.setOnClickListener{ finish() }

    }

    fun saveComment(contentsInput:TextView, ratingBar: RatingBar) {
        val contents = contentsInput!!.text.toString()
        val rating = ratingBar!!.rating
        val intent = Intent()

        intent.putExtra("contents", contents)
        intent.putExtra("rating", rating)
        setResult(RESULT_OK, intent)
        finish()
    }
}