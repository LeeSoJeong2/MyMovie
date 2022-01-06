package com.example.mymovie

import android.content.Intent
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
        val intent = Intent()

        intent.putExtra("contents", contents)
        intent.putExtra("rating", rating)
        setResult(RESULT_OK, intent)
        finish()
    }
}