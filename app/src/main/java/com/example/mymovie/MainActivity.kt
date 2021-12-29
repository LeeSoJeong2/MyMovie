package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
var items = ArrayList<CommentItem>()

class MainActivity : AppCompatActivity() {
    var likeButton: Button? = null
    var likeCountView: TextView? = null
    var likeCount = 0
    var likeState = false
    var unlikeButton: Button? = null
    var unlikeCountView: TextView? = null
    var unlikeCount = 0
    var unlikeState = false
    var commentView: RecyclerView? = null
    var writeButton: Button? = null
    var showButton: Button? = null
    var adapter:CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 좋아요 버튼 클릭 이벤트
        likeButton = findViewById<View>(R.id.likeBtn) as Button
        likeButton!!.setOnClickListener {
            if (likeState) {
                decrLikeCount()
            } else {
                incrLikeCount()
            }
            likeState = !likeState

            if (unlikeState) {
                decrUnlikeCount()
                unlikeState = !unlikeState
            }
        }

        likeCountView = findViewById<View>(R.id.likeText) as TextView
        likeCount = (likeCountView!!.text).toString().toInt()

        // 싫어요 버튼 클릭 이벤트
        unlikeButton = findViewById<View>(R.id.unlikeBtn) as Button
        unlikeButton!!.setOnClickListener {
            if (unlikeState) {
                decrUnlikeCount()
            } else {
                incrUnlikeCount()
            }
            unlikeState = !unlikeState

            if (likeState) {
                decrLikeCount()
                likeState = !likeState
            }
        }

        unlikeCountView = findViewById<View>(R.id.unlikeText) as TextView
        unlikeCount = (unlikeCountView!!.text).toString().toInt()

        // 한줄평 (recycler View)
        commentView = findViewById<View>(R.id.commentView) as RecyclerView

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        commentView!!.layoutManager = layoutManager

        adapter = CommentAdapter(applicationContext, items)

        adapter!!.addItem(CommentItem("kym71**", "19분전", 5.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))
        adapter!!.addItem(CommentItem("angel**", "18분전", 4.6f, "웃긴 내용보다는 좀 더 진지한 영화."))
        adapter!!.addItem(CommentItem("beaut**", "16분전", 4.3f, "연기가 부족한 느낌이 드는 배우도 있다. 그래도 전체적으로는 재밌다."))
        adapter!!.addItem(CommentItem("sales**", "15분전", 5.0f, "마지막이 비극인 영화."))
        adapter!!.addItem(CommentItem("pargo**", "9분전", 5.0f, "스트레스가 해소되는 영화네요."))

        commentView!!.adapter = adapter

        // 한줄평 작성하기
        writeButton = findViewById<View>(R.id.writeBtn) as Button
        writeButton!!.setOnClickListener{ showCommentWriteActivity() }

        // 한줄평 모두보기
        showButton = findViewById<View>(R.id.showBtn) as Button
        showButton!!.setOnClickListener{ showCommentListActivity() }

    }
    fun incrLikeCount() {
        likeCount += 1
        likeCountView!!.text = likeCount.toString()
        likeButton!!.setBackgroundResource(R.drawable.ic_thumb_up_selected)
    }

    fun decrLikeCount() {
        likeCount -= 1
        likeCountView!!.text = likeCount.toString()
        likeButton!!.setBackgroundResource(R.drawable.ic_thumb_up)
    }

    fun incrUnlikeCount() {
        unlikeCount += 1
        unlikeCountView!!.text = unlikeCount.toString()
        unlikeButton!!.setBackgroundResource(R.drawable.ic_thumb_down_selected)
    }

    fun decrUnlikeCount() {
        unlikeCount -= 1
        unlikeCountView!!.text = unlikeCount.toString()
        unlikeButton!!.setBackgroundResource(R.drawable.ic_thumb_down)
    }

    fun showCommentWriteActivity() {
        val intent = Intent(applicationContext, CommentWriteActivity::class.java)

        startActivityForResult(intent, 101)
    }

    fun showCommentListActivity() {
        val intent = Intent(applicationContext, CommentListActivity::class.java)

        startActivityForResult(intent, 102)
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