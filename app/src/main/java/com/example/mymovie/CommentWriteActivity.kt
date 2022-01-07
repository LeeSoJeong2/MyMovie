package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymovie.databinding.ActivityCommentWriteBinding

class CommentWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener { saveComment() }
        binding.cancelButton.setOnClickListener{ finish() }

    }

    private fun saveComment() {
        val contents = binding.contentsInput.text.toString()
        val rating = binding.ratingBar.rating

        MainActivity.adapter.addItem(CommentItem("kym71**", "0분전", rating, contents))
        MainActivity.adapter.notifyDataSetChanged()

        finish()
    }
}