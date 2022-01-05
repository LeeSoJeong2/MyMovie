package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.databinding.ActivityCommentListBinding
import com.example.mymovie.databinding.ActivityMainBinding

class CommentListActivity : AppCompatActivity() {
    var adapter: CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCommentListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.commentView.layoutManager = layoutManager

        adapter = CommentAdapter(applicationContext, items)
        binding.commentView.adapter = adapter

        // 한줄평 작성하기
        binding.writeBtn.setOnClickListener{ showCommentWriteActivity() }
    }

    fun showCommentWriteActivity() {
        val intent = Intent(applicationContext, CommentWriteActivity::class.java)

        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 101) {
            if (intent != null) {
                val contents = intent.getStringExtra("contents")
                val rating = intent.getFloatExtra("rating", 0.0f)
                adapter!!.addItem(CommentItem("kym71**", "0분전", rating, contents))
                adapter?.notifyDataSetChanged()
            }
        }
    }
}