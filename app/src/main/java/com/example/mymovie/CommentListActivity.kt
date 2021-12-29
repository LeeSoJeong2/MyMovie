package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommentListActivity : AppCompatActivity() {
    var commentView: RecyclerView? = null
    var writeButton: Button? = null
    var adapter: CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)

        commentView = findViewById<View>(R.id.commentView) as RecyclerView

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        commentView!!.layoutManager = layoutManager

        adapter = CommentAdapter(applicationContext, items)
        commentView!!.adapter = adapter

        // 한줄평 작성하기
        writeButton = findViewById<View>(R.id.writeBtn) as Button
        writeButton!!.setOnClickListener{ showCommentWriteActivity() }
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