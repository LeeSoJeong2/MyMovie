package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar

class CommentWriteActivity : AppCompatActivity() {
    var ratingBar: RatingBar? = null
    var contentsInput: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_write)

        ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar
        contentsInput = findViewById<View>(R.id.contentsInput) as EditText

        val saveButton = findViewById<View>(R.id.saveButton) as Button
        saveButton.setOnClickListener { saveComment() }

        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener{ finish() }

    }

    fun saveComment() {
        val contents = contentsInput!!.text.toString()
        val rating = ratingBar!!.rating
        val intent = Intent()

        intent.putExtra("contents", contents)
        intent.putExtra("rating", rating)
        setResult(RESULT_OK, intent)
        finish()
    }
}